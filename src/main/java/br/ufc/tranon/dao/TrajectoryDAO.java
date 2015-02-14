package br.ufc.tranon.dao;

import br.ufc.tranon.entity.PointOfTrajectory;

import java.util.List;

public interface TrajectoryDAO extends BaseDAO
{
	List<PointOfTrajectory> findPointsByTaxiIdAndDate(String experiment, Integer taxiId, String startDate, String finalDate)
			throws Exception;

	List<PointOfTrajectory> findRoadNetwork()
			throws Exception;

	String findTrajectoryByTaxiIdAndDate(String experiment, Integer taxiId, String startDate, String finalDate)
			throws Exception;

	List<PointOfTrajectory> findAllPointsByExperiment(String experiment)
			throws Exception;

	List<Long> findRoadNetworkPointsByExperiment(String experiment) 
			throws Exception;
}