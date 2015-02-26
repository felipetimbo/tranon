package br.ufc.tranon.entity;

import java.util.ArrayList;
import java.util.List;

public class RoadNetwork {

	List<RoadNetworkPoint> points;
	List<Long> pointsId;

	public RoadNetwork(){ }
	
	public RoadNetwork(List<PointOfTrajectory> allPointsOfTrajectory, List<Long> roadNetworkPointsId) {
		setPoints(new ArrayList<RoadNetworkPoint>());
		setPointsId(new ArrayList<Long>()); 
		
		for(Long pointId : roadNetworkPointsId){
			pointsId.add(pointId);
			
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
			points.add(rnp);
		}
	}

	public List<RoadNetworkPoint> getPoints() {
		return points;
	}

	public void setPoints(List<RoadNetworkPoint> points) {
		this.points = points;
	}

	public List<Long> getPointsId() {
		return pointsId;
	}

	public void setPointsId(List<Long> pointsId) {
		this.pointsId = pointsId;
	}
	
}
