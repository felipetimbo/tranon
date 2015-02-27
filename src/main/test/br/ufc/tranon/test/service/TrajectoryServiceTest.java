package br.ufc.tranon.test.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.ufc.tranon.dao.TrajectoryDAO;
import br.ufc.tranon.dao.impl.TrajectoryDAOImpl;
import br.ufc.tranon.entity.PointOfTrajectory;
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
	
//	@Test
	public void showAnonymizedTrajectoryTestOk() throws Exception
	{
		String experiment = "100k";
		int k = 5;
		int m = 2;
		
		long start = System.currentTimeMillis();

		service.showAnonymizedTrajectory(experiment, k, m);
		
		long elapsed = System.currentTimeMillis() - start;
		System.out.println(elapsed + " milisegundos");
	}
	
	@Test
	public void areaPoligonTest1Ok() throws Exception
	{
		
		PointOfTrajectory p1 = new PointOfTrajectory();
		p1.setLongitude(0.0);
		p1.setLatitude(0.0);
		p1.setLongitudeNn(1.0);
		p1.setLatitudeNn(0.0);
		
		PointOfTrajectory p2 = new PointOfTrajectory();
		p2.setLongitude(0.0);
		p2.setLatitude(1.0);
		p2.setLongitudeNn(1.0);
		p2.setLatitudeNn(1.0);
		
		PointOfTrajectory p3 = new PointOfTrajectory();
		p3.setLongitude(1.0);
		p3.setLatitude(2.0);
		p3.setLongitudeNn(1.0);
		p3.setLatitudeNn(1.0);
		
		PointOfTrajectory p4 = new PointOfTrajectory();
		p4.setLongitude(1.0);
		p4.setLatitude(3.0);
		p4.setLongitudeNn(1.0);
		p4.setLatitudeNn(2.0);
		
		List<PointOfTrajectory> l = new ArrayList<PointOfTrajectory>();
		l.add(p1);
		l.add(p2);
		l.add(p3);
		l.add(p4);
		
		System.out.println(service.calculateArea(l));
		
	}
	
	@Test
	public void areaPoligonTest2Ok() throws Exception
	{
		
		PointOfTrajectory p1 = new PointOfTrajectory();
		p1.setLongitude(0.0);
		p1.setLatitude(1.0);
		p1.setLongitudeNn(0.0);
		p1.setLatitudeNn(0.5);
		
		PointOfTrajectory p2 = new PointOfTrajectory();
		p2.setLongitude(2.0);
		p2.setLatitude(1.0);
		p2.setLongitudeNn(2.0);
		p2.setLatitudeNn(0.5);
		
		PointOfTrajectory p3 = new PointOfTrajectory();
		p3.setLongitude(1.0);
		p3.setLatitude(2.0);
		p3.setLongitudeNn(1.0);
		p3.setLatitudeNn(1.5);
		
		PointOfTrajectory p4 = new PointOfTrajectory();
		p4.setLongitude(1.0);
		p4.setLatitude(3.0);
		p4.setLongitudeNn(1.0);
		p4.setLatitudeNn(2.5);
		
		List<PointOfTrajectory> l = new ArrayList<PointOfTrajectory>();
		l.add(p1);
		l.add(p2);
		l.add(p3);
		l.add(p4);
		
		System.out.println(service.calculateArea(l));
		
	}
	
	@Test
	public void areaPoligonTest3Ok() throws Exception
	{
		
		PointOfTrajectory p1 = new PointOfTrajectory();
		p1.setLongitude(0.0);
		p1.setLatitude(0.0);
		p1.setLongitudeNn(1.0);
		p1.setLatitudeNn(0.0);
		
		PointOfTrajectory p2 = new PointOfTrajectory();
		p2.setLongitude(0.0);
		p2.setLatitude(1.0);
		p2.setLongitudeNn(1.0);
		p2.setLatitudeNn(1.0);
		
		PointOfTrajectory p3 = new PointOfTrajectory();
		p3.setLongitude(1.0);
		p3.setLatitude(2.0);
		p3.setLongitudeNn(2.0);
		p3.setLatitudeNn(2.0);
		
		PointOfTrajectory p4 = new PointOfTrajectory();
		p4.setLongitude(1.0);
		p4.setLatitude(3.0);
		p4.setLongitudeNn(2.0);
		p4.setLatitudeNn(3.0);
		
		List<PointOfTrajectory> l = new ArrayList<PointOfTrajectory>();
		l.add(p1);
		l.add(p2);
		l.add(p3);
		l.add(p4);
		
		System.out.println(service.calculateArea(l));
		
	}
	
	@Test
	public void areaPoligonTest4Ok() throws Exception
	{
		
		PointOfTrajectory p1 = new PointOfTrajectory();
		p1.setLongitude(0.0);
		p1.setLatitude(0.0);
		p1.setLongitudeNn(1.0);
		p1.setLatitudeNn(0.0);
		
		PointOfTrajectory p2 = new PointOfTrajectory();
		p2.setLongitude(1.0);
		p2.setLatitude(1.0);
		p2.setLongitudeNn(0.0);
		p2.setLatitudeNn(1.0);
		
		List<PointOfTrajectory> l = new ArrayList<PointOfTrajectory>();
		l.add(p1);
		l.add(p2);
		
		System.out.println(service.calculateArea(l));
		
	}
	
	@Test
	public void areaPoligonTest5Ok() throws Exception
	{
		
		PointOfTrajectory p1 = new PointOfTrajectory();
		p1.setLongitude(0.0);
		p1.setLatitude(0.0);
		p1.setLongitudeNn(0.0);
		p1.setLatitudeNn(-0.5);
		
		PointOfTrajectory p2 = new PointOfTrajectory();
		p2.setLongitude(2.0);
		p2.setLatitude(0.0);
		p2.setLongitudeNn(2.0);
		p2.setLatitudeNn(0.5);
		
		PointOfTrajectory p3 = new PointOfTrajectory();
		p3.setLongitude(1.0);
		p3.setLatitude(0.5);
		p3.setLongitudeNn(1.0);
		p3.setLatitudeNn(0.5);
		
		List<PointOfTrajectory> l = new ArrayList<PointOfTrajectory>();
		l.add(p1);
		l.add(p2);
		l.add(p3);
		
		System.out.println(service.calculateArea(l));
		
	}
	
	@Test
	public void areaPoligonTest6Ok() throws Exception
	{
		
		PointOfTrajectory p1 = new PointOfTrajectory();
		p1.setLongitude(0.0);
		p1.setLatitude(0.0);
		p1.setLongitudeNn(1.0);
		p1.setLatitudeNn(0.0);
		
		PointOfTrajectory p2 = new PointOfTrajectory();
		p2.setLongitude(0.0);
		p2.setLatitude(1.0);
		p2.setLongitudeNn(1.0);
		p2.setLatitudeNn(1.0);
		
		PointOfTrajectory p3 = new PointOfTrajectory();
		p3.setLongitude(1.0);
		p3.setLatitude(2.0);
		p3.setLongitudeNn(1.0);
		p3.setLatitudeNn(1.0);
		
		PointOfTrajectory p4 = new PointOfTrajectory();
		p4.setLongitude(2.0);
		p4.setLatitude(2.0);
		p4.setLongitudeNn(2.0);
		p4.setLatitudeNn(1.0);
		
		PointOfTrajectory p5 = new PointOfTrajectory();
		p5.setLongitude(3.0);
		p5.setLatitude(1.0);
		p5.setLongitudeNn(2.0);
		p5.setLatitudeNn(1.0);
		
		PointOfTrajectory p6 = new PointOfTrajectory();
		p6.setLongitude(3.0);
		p6.setLatitude(0.0);
		p6.setLongitudeNn(2.0);
		p6.setLatitudeNn(0.0);
		
		List<PointOfTrajectory> l = new ArrayList<PointOfTrajectory>();
		l.add(p1);
		l.add(p2);
		l.add(p3);
		l.add(p4);
		l.add(p5);
		l.add(p6);
		
		System.out.println(service.calculateArea(l));
		
	}
	
	@Test
	public void areaPoligonTest7Ok() throws Exception
	{
		
		PointOfTrajectory p1 = new PointOfTrajectory();
		p1.setLongitude(0.0);
		p1.setLatitude(0.0);
		p1.setLongitudeNn(0.0);
		p1.setLatitudeNn(1.0);
		
		PointOfTrajectory p2 = new PointOfTrajectory();
		p2.setLongitude(1.0);
		p2.setLatitude(0.0);
		p2.setLongitudeNn(1.0);
		p2.setLatitudeNn(1.0);
		
		PointOfTrajectory p3 = new PointOfTrajectory();
		p3.setLongitude(0.0);
		p3.setLatitude(0.0);
		p3.setLongitudeNn(0.0);
		p3.setLatitudeNn(1.0);
		
		List<PointOfTrajectory> l = new ArrayList<PointOfTrajectory>();
		l.add(p1);
		l.add(p2);
		l.add(p3);
		
		System.out.println(service.calculateArea(l));
		
	}
	
}
