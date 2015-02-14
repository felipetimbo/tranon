package br.ufc.tranon.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.ufc.tranon.dao.TrajectoryDAO;
import br.ufc.tranon.entity.PointOfTrajectory;
import br.ufc.tranon.entity.PointsSet;
import br.ufc.tranon.filter.Filter;
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
		
		for(int i=1; i <= m; i++){
			List<PointsSet> qids = findQIDsSizeI(allPointsOfTrajectory, roadNetworkPoints, k, i);
			System.out.println("QID tamanho " + i + ": ");
			for(PointsSet q : qids){
				System.out.println("pontos:" + q.getPoints() + ", suporte: " + q.getSupport());
			}
			System.out.println("total: " + qids.size() + " pontos");
			
		}
		
//		System.out.println(allPointsOfTrajectory.size());
		return null;
		
	}

	private List<PointsSet> findQIDsSizeI(List<PointOfTrajectory> allPointsOfTrajectory, List<Long> roadNetworkPoints, int k, int i) {
		
		List<PointsSet> qids = new ArrayList<PointsSet>();
		List<List<Long>> pointsSetSizeIList = Combination.getCombinations(roadNetworkPoints, i);
		
		for(List<Long> pointsSetSizeI : pointsSetSizeIList){
			Integer support = calculateSupport(pointsSetSizeI, allPointsOfTrajectory);
			if(support != 0 && support < k){
				qids.add(new PointsSet(pointsSetSizeI, i, support));
			}
		}
		
		return qids;
	}

	private Integer calculateSupport(List<Long> pointsSetSizeI,	List<PointOfTrajectory> allPointsOfTrajectory) {
		
		List<List<Integer>> trajectoriesList = new ArrayList<List<Integer>>();
		
		for(Long pointP : pointsSetSizeI){
			List<Integer> trajectoriesThatPassInP = new ArrayList<Integer>();
			
	        List<PointOfTrajectory> allPointsOfTrajectoryThatPassInP = new FilterList<Long>().
	        		filterList(allPointsOfTrajectory, PointOfTrajectoryFilters.taxiIdFilter(), pointP);
	        
	        for(PointOfTrajectory p : allPointsOfTrajectoryThatPassInP){
	        	if(!trajectoriesThatPassInP.contains(p.getTaxiId())){
	        		trajectoriesThatPassInP.add(p.getTaxiId());
	        	}
	        }
			
	        trajectoriesList.add(trajectoriesThatPassInP);
		}
		
		List<Integer> commons = new ArrayList<Integer>();
		commons.addAll(trajectoriesList.get(0));
	    for (ListIterator<List<Integer>> iter = trajectoriesList.listIterator(1); iter.hasNext(); ) {
	        commons.retainAll(iter.next());
	    }
	    
		return commons.size();
	}
	
	@Override
	public void setTrajectoryDAO(TrajectoryDAO trajectoryDAO) {
		this.trajectoryDAO = trajectoryDAO;
	}
}
