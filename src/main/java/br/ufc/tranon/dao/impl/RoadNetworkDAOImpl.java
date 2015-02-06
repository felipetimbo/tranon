package br.ufc.tranon.dao.impl;

import br.ufc.tranon.dao.RoadNetworkDAO;
import br.ufc.tranon.entity.PointOfTrajectory;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class RoadNetworkDAOImpl extends BaseDAOImpl implements RoadNetworkDAO
{
	public List<PointOfTrajectory> findPointsByTaxiIdAndDate(String experiment, Integer id, String startDate, String finalDate) 
			throws Exception
	{
		List<PointOfTrajectory> points = new ArrayList<PointOfTrajectory>();

		createStatement();

		String sql = "select taxi_id, date_time, longitude_nn, latitude_nn, "
				+ "ST_AsGeoJson(ST_MakePoint(longitude_nn, latitude_nn)) as geom "
				+ "from trajectories_" + experiment + " where taxi_id = " + id 
				+ " and date_time between '" + startDate + "' and '" + finalDate + "'";

		ResultSet r = this.s.executeQuery(sql);
		while (r.next())
		{
			PointOfTrajectory point = new PointOfTrajectory();
			point.setTaxiId((Integer)r.getObject(1));
			point.setDatetime((Date)r.getObject(2));
			point.setLongitude(Double.valueOf(((BigDecimal)r.getObject(3)).doubleValue()));
			point.setLatitude(Double.valueOf(((BigDecimal)r.getObject(4)).doubleValue()));
			point.setGeom((String)r.getObject(5));
			points.add(point);
		}
		closeStatement();

		return points;
	}

	public String findTrajectoryByTaxiIdAndDate(String experiment, Integer taxiId, String startDate, String finalDate)
			throws Exception
	{
		String trajectory = "";

		createStatement();

		String sql = "select ST_AsGeoJson(ST_MakeLine(points.geom)) from "
				+ "(select taxi_id, date_time, ST_MakePoint(longitude_nn, latitude_nn) as geom "
				+ "from trajectories_" + experiment + " where taxi_id = " 
				+ taxiId + " and date_time between '" + startDate + "' and '" + finalDate + "'" 
				+ ") as points";

		ResultSet r = this.s.executeQuery(sql);
		while (r.next()) {
			trajectory = (String)r.getObject(1);
		}
		closeStatement();

		return trajectory;
	}
}
