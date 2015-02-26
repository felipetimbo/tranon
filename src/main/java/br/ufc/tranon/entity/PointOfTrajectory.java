package br.ufc.tranon.entity;

import java.util.Date;
import org.postgis.PGgeometry;

public class PointOfTrajectory {
	
	private Integer taxiId;
	private Date datetime;
	private Double longitude;
	private Double latitude;
	private String geom;
	private PGgeometry point;
	private Long nn;
	private Double longitudeNn;
	private Double latitudeNn;

	public Integer getTaxiId() {
		return this.taxiId;
	}

	public void setTaxiId(Integer taxiId) {
		this.taxiId = taxiId;
	}

	public Date getDatetime() {
		return this.datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getGeom() {
		return this.geom;
	}

	public void setGeom(String geom) {
		this.geom = geom;
	}

	public PGgeometry getPoint() {
		return point;
	}

	public void setPoint(PGgeometry point) {
		this.point = point;
	}
	
	public Long getNn() {
		return nn;
	}

	public void setNn(Long nn) {
		this.nn = nn;
	}

	public Double getLongitudeNn() {
		return longitudeNn;
	}

	public void setLongitudeNn(Double longitudeNn) {
		this.longitudeNn = longitudeNn;
	}

	public Double getLatitudeNn() {
		return latitudeNn;
	}

	public void setLatitudeNn(Double latitudeNn) {
		this.latitudeNn = latitudeNn;
	}
	
}
