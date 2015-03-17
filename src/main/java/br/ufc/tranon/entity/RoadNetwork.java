package br.ufc.tranon.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;





import math.geom2d.Point2D;
import math.geom2d.point.KDTree2D;

public class RoadNetwork {

	private HashMap<Long, RoadNetworkPoint> points;
	private HashMap<Point2D, RoadNetworkPoint> points2d;

	public RoadNetwork(){ }
	
	public RoadNetwork(List<PointOfTrajectory> allPointsOfTrajectory, List<Long> roadNetworkPointsId) {
		
		points = new HashMap<Long, RoadNetworkPoint>();
		points2d = new HashMap<Point2D, RoadNetworkPoint>();
		
		for(Long pointId : roadNetworkPointsId){
			
			RoadNetworkPoint rnp = new RoadNetworkPoint();
			Set<Integer> trajectoriesList = Sets.newHashSet();
			
			for(PointOfTrajectory p : allPointsOfTrajectory){
				if(p.getNnId().equals(pointId)){
					if(!points.containsKey(p.getNnId())){
						rnp.setId(p.getNnId());
						rnp.setLatitude(p.getLatitude());
						rnp.setLongitude(p.getLongitude());
						rnp.setPoint(new Point2D(p.getLongitude(), p.getLatitude()));
					}
					if(!trajectoriesList.contains(p.getTaxiId())){
						trajectoriesList.add(p.getTaxiId());
					}
				}
			}
			
			rnp.setTrajectories(trajectoriesList);
			points.put(rnp.getId(), rnp);
			points2d.put(rnp.getPoint(), rnp);
		}
		
	}

	public HashMap<Long, RoadNetworkPoint> getPoints() {
		return points;
	}

	public void setPoints(HashMap<Long, RoadNetworkPoint> points) {
		this.points = points;
	}

//	@SuppressWarnings("unchecked")
	public KDTree2D getKdtree() {
		Set<Point2D> points2dSet = points2d.keySet();
		return new KDTree2D((ArrayList<Point2D>) Lists.newArrayList(points2dSet));
	}

//	
//	public List<Point2D> getAllPoints2D() {
//		List<Point2D> points2d = new ArrayList<Point2D>();
//		for(RoadNetworkPoint rnp : points){
//			points2d.add(rnp.getPoint());
//		}
//		return points2d;
//	}
	
	/**
	 * Return point by a Point2d in Road Network
	 * @param point2d
	 * @return
	 */
	public RoadNetworkPoint getPoint(Point2D point2d){
		return points2d.get(point2d);
	}
	
	/**
	 * Return point by a pointId in Road Network
	 * @param pointId
	 * @return
	 */
	public RoadNetworkPoint getPoint(Long pointId){
		return points.get(pointId);
	}
	
	public void removePoint(RoadNetworkPoint rnp){
		points.remove(rnp.getId());
		points2d.remove(rnp.getPoint());
	}
	
}
