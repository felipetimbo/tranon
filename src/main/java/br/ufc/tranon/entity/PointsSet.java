package br.ufc.tranon.entity;

import java.util.List;

public class PointsSet {

	private List<Long> points;
	private int size;
	private int support;
	
	public PointsSet(List<Long> points, int size, int support) {
		this.points = points;
		this.size = size;
		this.support = support;
	}

	public List<Long> getPoints() {
		return points;
	}
	
	public void setPoints(List<Long> points) {
		this.points = points;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getSupport() {
		return support;
	}
	
	public void setSupport(int support) {
		this.support = support;
	}
	
}
