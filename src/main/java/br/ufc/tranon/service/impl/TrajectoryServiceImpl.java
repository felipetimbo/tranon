package br.ufc.tranon.service.impl;

import br.ufc.tranon.dao.TrajectoryDAO;
import br.ufc.tranon.entity.PointOfTrajectory;
import br.ufc.tranon.service.TrajectoryService;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class TrajectoryServiceImpl implements TrajectoryService
{
	@Inject
	private TrajectoryDAO trajectoryDAO;

	public List<PointOfTrajectory> showOriginalPoints(String experiment, Integer taxiId, String startDate, String finalDate) throws Exception
	{
		return trajectoryDAO.findPointsByTaxiIdAndDate(experiment, taxiId, startDate, finalDate);
	}

	public String showOriginalTrajectory(String experiment, Integer taxiId, String startDate, String finalDate) throws Exception
	{
		return trajectoryDAO.findTrajectoryByTaxiIdAndDate(experiment, taxiId, startDate, finalDate);
	}

	public List<PointOfTrajectory> showRoadNetwork() throws Exception
	{
		return trajectoryDAO.findRoadNetwork();
	}
}
