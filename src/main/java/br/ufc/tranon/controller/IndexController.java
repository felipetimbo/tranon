package br.ufc.tranon.controller;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.ufc.tranon.entity.PointOfTrajectory;
import br.ufc.tranon.service.RoadNetworkService;
import br.ufc.tranon.service.TrajectoryService;

@Controller
public class IndexController {
	private final Result result;
	
	@Inject
	private TrajectoryService trajectoryService;
	
	@Inject
	private RoadNetworkService roadNetworkService;

	/**
	 * @deprecated
	 */
	protected IndexController() {
		this(null);
	}

	@Inject
	public IndexController(Result result) {
		this.result = result;
	}

	@Path({"/"})
	public void index() {
		String vraptor = "funciona";
		this.result.include("variable", vraptor);
	}

	public void showOriginalPoints(String experiment, Integer taxiId, String startDate, String finalDate)
			throws Exception {
		List<PointOfTrajectory> originalPoints = trajectoryService.showOriginalPoints(experiment, taxiId, startDate, finalDate);
		result.use(Results.json()).from(originalPoints, "originalPoints").serialize();
	}

	public void showOriginalTrajectory(String experiment, Integer taxiId, String startDate, String finalDate)
			throws Exception {
		String originalTrajectory = trajectoryService.showOriginalTrajectory(experiment, taxiId, startDate, finalDate);
		result.use(Results.json()).from(originalTrajectory, "originalTrajectory").serialize();
	}

	public void showRoadNetwork() throws Exception{
		List<PointOfTrajectory> roadNetwork = trajectoryService.showRoadNetwork();
		result.use(Results.json()).from(roadNetwork, "roadNetwork").serialize();
	}

	public void showSimplifiedPoints(String experiment, Integer taxiId, String startDate, String finalDate)
			throws Exception {
		List<PointOfTrajectory> simplifiedPoints = roadNetworkService.showSimplifiedPoints(experiment, taxiId, startDate, finalDate);
		result.use(Results.json()).from(simplifiedPoints, "simplifiedPoints").serialize();
	}

	public void showSimplifiedTrajectory(String experiment, Integer taxiId, String startDate, String finalDate)
			throws Exception {
		String simplifiedTrajectory = roadNetworkService.showSimplifiedTrajectory(experiment, taxiId, startDate, finalDate);
		result.use(Results.json()).from(simplifiedTrajectory, "simplifiedTrajectory").serialize();
	}
}
