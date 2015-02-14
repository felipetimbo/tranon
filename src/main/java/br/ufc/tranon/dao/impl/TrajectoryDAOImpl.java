package br.ufc.tranon.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import org.postgis.PGgeometry;

import br.ufc.tranon.dao.TrajectoryDAO;
import br.ufc.tranon.entity.PointOfTrajectory;

@RequestScoped
public class TrajectoryDAOImpl extends BaseDAOImpl implements TrajectoryDAO
{
	@Override
	public List<PointOfTrajectory> findPointsByTaxiIdAndDate(String experiment, Integer id, String startDate, String finalDate) throws Exception
	{
		List<PointOfTrajectory> points = new ArrayList<PointOfTrajectory>();

		createStatement();

		String sql = "select taxi_id, date_time, longitude, latitude, ST_AsGeoJson(ST_MakePoint(longitude, latitude)) as geom"
				+ " from trajectories_" + experiment 
				+ " where taxi_id = " + id + " and date_time between '" + startDate + "' and '" + finalDate + "'";

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

	@Override
	public List<PointOfTrajectory> findRoadNetwork() throws Exception
	{
		List<PointOfTrajectory> points = new ArrayList<PointOfTrajectory>();

		File nodes = new File("C:\\beijing_nodes.txt");
		BufferedReader in = new BufferedReader(new FileReader(nodes));
		while (in.ready())
		{
			String s = in.readLine();
			String[] parts = s.split("\\,");

			Double lat = Double.valueOf(Double.parseDouble(parts[1]));
			Double lng = Double.valueOf(Double.parseDouble(parts[2]));

			PointOfTrajectory point = new PointOfTrajectory();
			point.setLongitude(lng);
			point.setLatitude(lat);

			point.setGeom("{\"type\":\"Point\",\"coordinates\":[" + lng + "," + lat + "]}");

			points.add(point);
		}
		in.close();

		return points;
	}

	@Override
	public String findTrajectoryByTaxiIdAndDate(String experiment, Integer taxiId, String startDate, String finalDate)	throws Exception
	{
		String trajectory = "";

		createStatement();

		String sql = "select ST_AsGeoJson(ST_MakeLine(points.geom)) from ("
				+ "select taxi_id, date_time, ST_MakePoint(longitude, latitude) as geom "
				+ "from trajectories_" + experiment + " where taxi_id = " + taxiId 
				+ " and date_time between '" + startDate + "' and '" + finalDate + "'" + 
				") as points";

		ResultSet r = this.s.executeQuery(sql);
		while (r.next()) {
			trajectory = (String)r.getObject(1);
		}
		closeStatement();

		return trajectory;
	}
	
	@Override
	public List<PointOfTrajectory> findAllPointsByExperiment(String experiment) throws Exception
	{
		List<PointOfTrajectory> points = new ArrayList<PointOfTrajectory>();

		createStatement();

		String sql = "select taxi_id, date_time, longitude, latitude, ST_AsGeoJson(ST_MakePoint(longitude, latitude)) as geom, "
				+ "ST_MakePoint(longitude, latitude) as point, nn, longitude_nn, latitude_nn"
				+ " from trajectories_" + experiment ;

		ResultSet r = this.s.executeQuery(sql);
		while (r.next())
		{
			PointOfTrajectory point = new PointOfTrajectory();
			point.setTaxiId((Integer)r.getObject(1));
			point.setDatetime((Date)r.getObject(2));
			point.setLongitude(Double.valueOf(((BigDecimal)r.getObject(3)).doubleValue()));
			point.setLatitude(Double.valueOf(((BigDecimal)r.getObject(4)).doubleValue()));
			point.setGeom((String)r.getObject(5));
			point.setPoint((PGgeometry)r.getObject(6));
			point.setNn((Long)r.getObject(7));
			point.setLongitudeNn(Double.valueOf(((BigDecimal)r.getObject(8)).doubleValue()));
			point.setLatitudeNn(Double.valueOf(((BigDecimal)r.getObject(9)).doubleValue()));
			points.add(point);
		}
		closeStatement();

		return points;
	}  

	@Override
	public List<Long> findRoadNetworkPointsByExperiment(String experiment) throws Exception {
		List<Long> roadNetworkPoints = new ArrayList<Long>();

		createStatement();

		String sql = "select distinct nn from trajectories_" + experiment ;

		ResultSet r = this.s.executeQuery(sql);
		while (r.next())
		{
			roadNetworkPoints.add((Long) r.getObject(1) );
		}
		closeStatement();

		return roadNetworkPoints;
	}
}
