package br.ufc.tranon.service;

import br.ufc.tranon.entity.PointOfTrajectory;

import java.util.List;

public interface RoadNetworkService
{
	public List<PointOfTrajectory> showSimplifiedPoints(String experiment, Integer taxiId, String startDate, String finalDate)
			throws Exception;

	public String showSimplifiedTrajectory(String experiment, Integer taxiId, String startDate, String finalDate)
			throws Exception;
}