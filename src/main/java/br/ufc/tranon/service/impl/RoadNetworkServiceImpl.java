package br.ufc.tranon.service.impl;

import br.ufc.tranon.dao.RoadNetworkDAO;
import br.ufc.tranon.entity.PointOfTrajectory;
import br.ufc.tranon.service.RoadNetworkService;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class RoadNetworkServiceImpl implements RoadNetworkService
{

	@Inject
	private RoadNetworkDAO roadNetworkDAO;

	public List<PointOfTrajectory> showSimplifiedPoints(Integer taxiId, String startDate, String finalDate) throws Exception
	{
		return this.roadNetworkDAO.findPointsByTaxiIdAndDate(taxiId, startDate, finalDate);
	}

	public String showSimplifiedTrajectory(Integer taxiId, String startDate, String finalDate) throws Exception
	{
		return this.roadNetworkDAO.findTrajectoryByTaxiIdAndDate(taxiId, startDate, finalDate);
	}
}