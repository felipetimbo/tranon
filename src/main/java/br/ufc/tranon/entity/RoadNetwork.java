package br.ufc.tranon.entity;

import java.util.List;

public class RoadNetwork {

	List<RoadNetworkPoint> points;
	List<Long> pointsId;

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
