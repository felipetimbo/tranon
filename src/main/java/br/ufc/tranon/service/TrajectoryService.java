package br.ufc.tranon.service;

import br.ufc.tranon.dao.TrajectoryDAO;
import br.ufc.tranon.entity.PointOfTrajectory;

import java.util.List;

public abstract interface TrajectoryService
{
	List<PointOfTrajectory> showOriginalPoints(String experiment, Integer taxiId, String startDate, String finalDate)
			throws Exception;

	String showOriginalTrajectory(String experiment, Integer taxiId, String startDate, String finalDate)
			throws Exception;

	List<PointOfTrajectory> showRoadNetwork()
			throws Exception;

	List<PointOfTrajectory> showAnonymizedTrajectory(String experiment, int k,
			int m) throws Exception;

	void setTrajectoryDAO(TrajectoryDAO trajectoryDAO);
}
