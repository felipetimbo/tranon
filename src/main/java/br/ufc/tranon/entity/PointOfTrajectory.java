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
	private Long nnId;
	private Double longitudeNn;
	private Double latitudeNn;
	private Long anonId;
	private Double longitudeAn;
	private Double latitudeAn;

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
	
	public Long getNnId() {
		return nnId;
	}

	public void setNnId(Long nn) {
		this.nnId = nn;
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

	public Long getAnonId() {
		return anonId;
	}

	public void setAnonId(Long anonId) {
		this.anonId = anonId;
	}

	public Double getLongitudeAn() {
		return longitudeAn;
	}

	public void setLongitudeAn(Double longitudeAn) {
		this.longitudeAn = longitudeAn;
	}

	public Double getLatitudeAn() {
		return latitudeAn;
	}

	public void setLatitudeAn(Double latitudeAn) {
		this.latitudeAn = latitudeAn;
	}
	
}
