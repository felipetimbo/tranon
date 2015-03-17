package br.ufc.tranon.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import math.geom2d.Point2D;
import math.geom2d.polygon.MultiPolygon2D;
import math.geom2d.polygon.Polygon2D;
import math.geom2d.polygon.Polygons2D;
import math.geom2d.polygon.Polyline2D;
import math.geom2d.polygon.SimplePolygon2D;
import br.ufc.tranon.dao.TrajectoryDAO;
import br.ufc.tranon.entity.PointOfTrajectory;
import br.ufc.tranon.entity.RoadNetwork;
import br.ufc.tranon.entity.RoadNetworkPoint;
import br.ufc.tranon.service.TrajectoryService;
import br.ufc.tranon.util.Combinations;

import com.google.common.collect.Sets;

@RequestScoped
public class TrajectoryServiceImpl implements TrajectoryService
{
	@Inject
	private TrajectoryDAO trajectoryDAO;

	private RoadNetwork roadNetwork;
	
	@Override
	public List<PointOfTrajectory> showOriginalPoints(String experiment, Integer taxiId, String startDate, String finalDate) throws Exception {
		return trajectoryDAO.findPointsByTaxiIdAndDate(experiment, taxiId, startDate, finalDate);
	}

	@Override
	public String showOriginalTrajectory(String experiment, Integer taxiId, String startDate, String finalDate) throws Exception {
		return trajectoryDAO.findTrajectoryByTaxiIdAndDate(experiment, taxiId, startDate, finalDate);
	}

	@Override
	public List<PointOfTrajectory> showRoadNetwork() throws Exception {
		return trajectoryDAO.findRoadNetwork();
	}
	
	@Override
	public List<PointOfTrajectory> showAnonymizedTrajectory(String experiment, int k, int m) throws Exception {

		/** TODO
		 * Improve performance of these 3 methods
		 */
		List<PointOfTrajectory> allPointsOfTrajectory = trajectoryDAO.findAllPointsByExperiment(experiment);
		List<Long> roadNetworkPointsId = trajectoryDAO.findRoadNetworkPointsByExperiment(experiment);
		roadNetwork = new RoadNetwork(allPointsOfTrajectory, roadNetworkPointsId); 

		for(int i=1; i <= m; i++){

			Combinations<Long> pointsSetSizeIList = new Combinations<Long>(roadNetwork.getPoints().keySet(), i);

			for(List<Long> pointsSetSizeI : pointsSetSizeIList){
				// IDEIA: pre-processar os pontos desnecessários if(sup == 0) com spark!
				// armazenar uma lista com os ja processados 

				Set<Integer> commonsTrajectories = getCommonsTrajectories(pointsSetSizeI);
				Integer support = commonsTrajectories.size();

				if(support != 0 && support < k){

					for(int j = 0 ; j < pointsSetSizeI.size(); j++ ){

						RoadNetworkPoint rnp = roadNetwork.getPoint(pointsSetSizeI.get(j));
						roadNetwork.removePoint(rnp);

						Point2D nearestNeighbor = roadNetwork.getKdtree().nearestNeighbor(rnp.getPoint());
						roadNetwork.getPoint(nearestNeighbor).getTrajectories().addAll(rnp.getTrajectories());

						pointsSetSizeIList.setIndicesNaoComputaveis(pointsSetSizeI.get(j));
					}
				}
			}

			System.out.println(roadNetwork.getPoints().size());

		}

		return null;

	}

	private Set<Integer> getCommonsTrajectories(List<Long> pointsSetSizeI) {
		
		List<Set<Integer>> trajectoriesList = new ArrayList<Set<Integer>>();
		
		for(Long pointP : pointsSetSizeI){
			
			trajectoriesList.add(roadNetwork.getPoint(pointP).getTrajectories());
		}
		
		Set<Integer> commonsTrajectories = Sets.newHashSet(trajectoriesList.get(0));
		
		for(int i = 1 ; i < trajectoriesList.size() ; i++){
			Set<Integer> aux = (Sets.intersection(commonsTrajectories, trajectoriesList.get(i)));
			commonsTrajectories = Sets.newHashSet(aux);
		}
		
		return commonsTrajectories;
	}
	
	
	public Double calculateInformationLoss(List<PointOfTrajectory> trajectory) {
		Polygon2D informationLoss = new MultiPolygon2D();
		
		for(int i=0 ; i < trajectory.size()-1; i++) {
				
			Point2D originalOrigin = new Point2D(trajectory.get(i).getLongitude(), trajectory.get(i).getLatitude());
			Point2D originalDestination = new Point2D(trajectory.get(i+1).getLongitude(), trajectory.get(i+1).getLatitude());
			Point2D generalizedOrigin = new Point2D(trajectory.get(i).getLongitudeNn(), trajectory.get(i).getLatitudeNn());
			Point2D generalizedDestination = new Point2D(trajectory.get(i+1).getLongitudeNn(), trajectory.get(i+1).getLatitudeNn());
			
			Polyline2D originalLine = new Polyline2D(originalOrigin, originalDestination);
			Polyline2D generalizedLine = new Polyline2D(generalizedOrigin, generalizedDestination);
			
			// if the points don't intersect
			if(originalLine.intersections(generalizedLine.firstEdge()).isEmpty()){
				
				informationLoss = Polygons2D.union(informationLoss, new SimplePolygon2D(originalOrigin, originalDestination, generalizedDestination, generalizedOrigin)); 
				
			} else {
				
				List<Point2D> intersection = (List<Point2D>) originalLine.intersections(generalizedLine.firstEdge());
				informationLoss = Polygons2D.union(informationLoss, new SimplePolygon2D(originalOrigin, intersection.get(0), generalizedOrigin));
				informationLoss = Polygons2D.union(informationLoss, new SimplePolygon2D(originalDestination, intersection.get(0), generalizedDestination));
				
			}
				
		}
		
		return informationLoss.area();
	}
	
	@Override
	public void setTrajectoryDAO(TrajectoryDAO trajectoryDAO) {
		this.trajectoryDAO = trajectoryDAO;
	}
}
