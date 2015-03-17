package br.ufc.tranon.entity;

import java.util.Set;

import math.geom2d.Point2D;

public class RoadNetworkPoint {

	private Long id;
	private Double longitude;
	private Double latitude;
	private Point2D point;
	private Set<Integer> trajectories;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	public Double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	public Point2D getPoint() {
		return point;
	}

	public void setPoint(Point2D point) {
		this.point = point;
	}

	public Set<Integer> getTrajectories() {
		return trajectories;
	}

	public void setTrajectories(Set<Integer> trajectories) {
		this.trajectories = trajectories;
	}

}
