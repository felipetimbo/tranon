package br.ufc.tranon.test.service;

import org.junit.Before;
import org.junit.Test;

import br.ufc.tranon.dao.TrajectoryDAO;
import br.ufc.tranon.dao.impl.TrajectoryDAOImpl;
import br.ufc.tranon.service.TrajectoryService;
import br.ufc.tranon.service.impl.TrajectoryServiceImpl;

public class TrajectoryServiceTest {

	private TrajectoryService service;
	private TrajectoryDAO dao;

	@Before
	public void init()
	{
		service = new TrajectoryServiceImpl();
		dao = new TrajectoryDAOImpl();
		service.setTrajectoryDAO(dao);
	}
	
	@Test
	public void showAnonymizedTrajectoryTestOk() throws Exception
	{
		String experiment = "100k";
		int k = 2;
		int m = 1;
		
		long start = System.currentTimeMillis();

		service.showAnonymizedTrajectory(experiment, k, m);
		
		long elapsed = System.currentTimeMillis() - start;
		System.out.println(elapsed + " milisegundos");
	}
	
}
