package br.ufc.tranon.entity;

import java.util.List;

public class RoadNetworkPoint {

	private Long id;
	private Double longitude;
	private Double latitude;
	private List<Integer> trajectories;
	
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
	
	public List<Integer> getTrajectories() {
		return trajectories;
	}
	
	public void setTrajectories(List<Integer> trajectories) {
		this.trajectories = trajectories;
	}
	
}
