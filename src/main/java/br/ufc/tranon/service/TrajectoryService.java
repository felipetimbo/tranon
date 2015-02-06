package br.ufc.tranon.service;

import br.ufc.tranon.entity.PointOfTrajectory;

import java.util.List;

public abstract interface TrajectoryService
{
	public List<PointOfTrajectory> showOriginalPoints(String experiment, Integer taxiId, String startDate, String finalDate)
			throws Exception;

	public String showOriginalTrajectory(String experiment, Integer taxiId, String startDate, String finalDate)
			throws Exception;

	public List<PointOfTrajectory> showRoadNetwork()
			throws Exception;
}
