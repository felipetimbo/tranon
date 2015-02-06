package br.ufc.tranon.dao;

import br.ufc.tranon.entity.PointOfTrajectory;

import java.util.List;

public interface RoadNetworkDAO
{
	List<PointOfTrajectory> findPointsByTaxiIdAndDate(String experiment, Integer taxiId, String startDate, String finalDate)
			throws Exception;

	String findTrajectoryByTaxiIdAndDate(String experiment, Integer taxiId, String startDate, String finalDate)
			throws Exception;
}