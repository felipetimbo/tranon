package br.ufc.tranon.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import math.geom2d.Point2D;
import math.geom2d.polygon.Polyline2D;
import math.geom2d.polygon.SimplePolygon2D;
import br.ufc.tranon.dao.TrajectoryDAO;
import br.ufc.tranon.entity.PointOfTrajectory;
import br.ufc.tranon.entity.PointsSet;
import br.ufc.tranon.entity.RoadNetwork;
import br.ufc.tranon.entity.RoadNetworkPoint;
import br.ufc.tranon.filter.FilterList;
import br.ufc.tranon.filter.PointOfTrajectoryFilters;
import br.ufc.tranon.service.TrajectoryService;
import br.ufc.tranon.util.Combination;

@RequestScoped
public class TrajectoryServiceImpl implements TrajectoryService
{
	@Inject
	private TrajectoryDAO trajectoryDAO;

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
		List<PointOfTrajectory> allPointsOfTrajectory = trajectoryDAO.findAllPointsByExperiment(experiment);
		List<Long> roadNetworkPoints = trajectoryDAO.findRoadNetworkPointsByExperiment(experiment);
		
		long start = System.currentTimeMillis();

		RoadNetwork roadNetwork = updateRoadNetwork(allPointsOfTrajectory, roadNetworkPoints);
		
		long elapsed = System.currentTimeMillis() - start;
		System.out.println(elapsed + " milisegundos");
		
		for(int i=1; i <= m; i++){
			List<PointsSet> qids = findQIDsSizeI(roadNetwork, k, i);
			System.out.println("QID tamanho " + i + ": ");
//			for(PointsSet q : qids){
//				System.out.println("pontos:" + q.getPoints() + ", suporte: " + q.getSupport());
//			}
			System.out.println("total: " + qids.size() + " pontos");
			
		}
		
		return null;
		
	}

	private RoadNetwork updateRoadNetwork(List<PointOfTrajectory> allPointsOfTrajectory, List<Long> roadNetworkPoints) {
		RoadNetwork rn = new RoadNetwork();
		rn.setPoints(new ArrayList<RoadNetworkPoint>());
		rn.setPointsId(new ArrayList<Long>());
		
		for(Long pointId : roadNetworkPoints){
			rn.getPointsId().add(pointId);
			
			RoadNetworkPoint rnp = new RoadNetworkPoint();
			List<Integer> trajectoriesList = new ArrayList<Integer>();
			
			for(PointOfTrajectory p : allPointsOfTrajectory){
				if(p.getNn().equals(pointId)){
					if(rnp.getId() == null){
						rnp.setId(p.getNn());
						rnp.setLatitude(p.getLatitude());
						rnp.setLongitude(p.getLongitude());
					}
					if(!trajectoriesList.contains(p.getTaxiId())){
						trajectoriesList.add(p.getTaxiId());
					}
				}
			}
			
			rnp.setTrajectories(trajectoriesList);
			rn.getPoints().add(rnp);
		}
		
		return rn;
	}

	private List<PointsSet> findQIDsSizeI(RoadNetwork roadNetwork, int k, int m) {
		
		List<PointsSet> qids = new ArrayList<PointsSet>();
		List<List<Long>> pointsSetSizeIList = Combination.getCombinations(roadNetwork.getPointsId(), m);
		
		for(List<Long> pointsSetSizeI : pointsSetSizeIList){
			List<Integer> commonsTrajectories = calculateSupport(pointsSetSizeI, roadNetwork);
			Integer support = commonsTrajectories.size();
			
			if(support != 0 && support < k){
				PointsSet qid = new PointsSet(pointsSetSizeI, m, support);
				qids.add(qid);
				System.out.println("pontos:" + qid.getPoints() + ", suporte: " + qid.getSupport());
			}
			
		}
		
		return qids;
	}

	private List<Integer> calculateSupport(List<Long> pointsSetSizeI, RoadNetwork roadNetwork) {
		
		List<List<Integer>> trajectoriesList = new ArrayList<List<Integer>>();
		
		for(Long pointP : pointsSetSizeI){
			
			List<RoadNetworkPoint> nearestNeighbors = new FilterList<Long>().
		        		filterList(roadNetwork.getPoints(), PointOfTrajectoryFilters.nearestNeighborFilter(), pointP);
			
	        trajectoriesList.add(nearestNeighbors.get(0).getTrajectories());
		}
		
		List<Integer> commonsTrajectories = new ArrayList<Integer>();
		commonsTrajectories.addAll(trajectoriesList.get(0));
	    for (ListIterator<List<Integer>> iter = trajectoriesList.listIterator(1); iter.hasNext(); ) {
	        commonsTrajectories.retainAll(iter.next());
	    }
	    
		return commonsTrajectories;
	}
	
	
	@Override
	public Double calculateArea(List<PointOfTrajectory> trajectory){
		Double area = 0d;

		for(int i=0 ; i < trajectory.size()-1; i++){
				
			Point2D originalOrigin = new Point2D(trajectory.get(i).getLongitude(), trajectory.get(i).getLatitude());
			Point2D originalDestination = new Point2D(trajectory.get(i+1).getLongitude(), trajectory.get(i+1).getLatitude());
			Point2D generalizedOrigin = new Point2D(trajectory.get(i).getLongitudeNn(), trajectory.get(i).getLatitudeNn());
			Point2D generalizedDestination = new Point2D(trajectory.get(i+1).getLongitudeNn(), trajectory.get(i+1).getLatitudeNn());
			
			Polyline2D originalLine = new Polyline2D(originalOrigin, originalDestination);
			Polyline2D generalizedLine = new Polyline2D(generalizedOrigin, generalizedDestination);
			
			// if the two points don't intersect
			if(originalLine.intersections(generalizedLine.firstEdge()).isEmpty()){
				
				area += Math.abs(new SimplePolygon2D(originalOrigin, originalDestination, generalizedDestination, generalizedOrigin).area());
				
			} else {
				
				List<Point2D> intersection = (List<Point2D>) originalLine.intersections(generalizedLine.firstEdge());
				area += Math.abs(new SimplePolygon2D(originalOrigin, intersection.get(0), generalizedOrigin).area());
				area += Math.abs(new SimplePolygon2D(originalDestination, intersection.get(0), generalizedDestination).area());
				
			}
				
		}
		
		return area;
	}
	
	@Override
	public void setTrajectoryDAO(TrajectoryDAO trajectoryDAO) {
		this.trajectoryDAO = trajectoryDAO;
	}
}
