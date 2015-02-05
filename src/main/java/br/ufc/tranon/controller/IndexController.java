package br.ufc.tranon.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.serialization.JSONSerialization;
import br.com.caelum.vraptor.view.Results;
import br.ufc.tranon.entity.PointOfTrajectory;
import br.ufc.tranon.service.RoadNetworkService;
import br.ufc.tranon.service.TrajectoryService;
import java.util.List;
import javax.inject.Inject;

@Controller
public class IndexController
{
	private final Result result;
	
	@Inject
	private TrajectoryService trajectoryService;
	@Inject
	private RoadNetworkService roadNetworkService;

	/**
	 * @deprecated
	 */
	protected IndexController()
	{
		this(null);
	}

	@Inject
	public IndexController(Result result)
	{
		this.result = result;
	}

	@Path({"/"})
	public void index()
	{
		String vraptor = "funciona";
		this.result.include("variable", vraptor);
	}

	public void showOriginalPoints(Integer taxiId, String startDate, String finalDate)
			throws Exception
	{
		List<PointOfTrajectory> originalPoints = this.trajectoryService.showOriginalPoints(taxiId, startDate, finalDate);
		result.use(Results.json()).from(originalPoints, "originalPoints").serialize();
	}

	public void showOriginalTrajectory(Integer taxiId, String startDate, String finalDate)
			throws Exception
	{
		String originalTrajectory = this.trajectoryService.showOriginalTrajectory(taxiId, startDate, finalDate);
		result.use(Results.json()).from(originalTrajectory, "originalTrajectory").serialize();
	}

	public void showRoadNetwork()
			throws Exception
	{
		List<PointOfTrajectory> roadNetwork = this.trajectoryService.showRoadNetwork();
		result.use(Results.json()).from(roadNetwork, "roadNetwork").serialize();
	}

	public void showSimplifiedPoints(Integer taxiId, String startDate, String finalDate)
			throws Exception
	{
		List<PointOfTrajectory> simplifiedPoints = this.roadNetworkService.showSimplifiedPoints(taxiId, startDate, finalDate);
		result.use(Results.json()).from(simplifiedPoints, "simplifiedPoints").serialize();
	}

	public void showSimplifiedTrajectory(Integer taxiId, String startDate, String finalDate)
			throws Exception
	{
		String simplifiedTrajectory = this.roadNetworkService.showSimplifiedTrajectory(taxiId, startDate, finalDate);
		((JSONSerialization)this.result.use(Results.json())).from(simplifiedTrajectory, "simplifiedTrajectory").serialize();
	}
}
