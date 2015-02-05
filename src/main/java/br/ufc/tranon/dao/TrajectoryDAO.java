package br.ufc.tranon.dao;

import br.ufc.tranon.entity.PointOfTrajectory;
import java.util.List;

public interface TrajectoryDAO extends BaseDAO
{
	List<PointOfTrajectory> findPointsByTaxiIdAndDate(Integer taxiId, String startDate, String finalDate)
			throws Exception;

	List<PointOfTrajectory> findRoadNetwork()
			throws Exception;

	String findTrajectoryByTaxiIdAndDate(Integer taxiId, String startDate, String finalDate)
			throws Exception;
}