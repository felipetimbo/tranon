package br.ufc.tranon.entity;

import java.util.Date;

public class PointOfTrajectory
{
	private Integer taxiId;
	private Date datetime;
	private Double longitude;
	private Double latitude;
	private String geom;

	public Integer getTaxiId()
	{
		return this.taxiId;
	}

	public void setTaxiId(Integer taxiId)
	{
		this.taxiId = taxiId;
	}

	public Date getDatetime()
	{
		return this.datetime;
	}

	public void setDatetime(Date datetime)
	{
		this.datetime = datetime;
	}

	public Double getLongitude()
	{
		return this.longitude;
	}

	public void setLongitude(Double longitude)
	{
		this.longitude = longitude;
	}

	public Double getLatitude()
	{
		return this.latitude;
	}

	public void setLatitude(Double latitude)
	{
		this.latitude = latitude;
	}

	public String getGeom()
	{
		return this.geom;
	}

	public void setGeom(String geom)
	{
		this.geom = geom;
	}
}
