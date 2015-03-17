package br.ufc.tranon.test.service;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import br.ufc.tranon.dao.TrajectoryDAO;
import br.ufc.tranon.dao.impl.TrajectoryDAOImpl;
import br.ufc.tranon.entity.PointOfTrajectory;
import br.ufc.tranon.service.TrajectoryService;
import br.ufc.tranon.service.impl.TrajectoryServiceImpl;

import com.google.common.base.Charsets;

public class TrajectoryServiceTest {

	private TrajectoryService service;
	private TrajectoryDAO dao;
	private String path = "C:\\Texto";

	@Before
	public void init()
	{
		service = new TrajectoryServiceImpl();
		dao = new TrajectoryDAOImpl();
		service.setTrajectoryDAO(dao);
	}
	
	@Test
	public void showAnonymizedTrajectoryTestOk() throws Exception {
		String experiment = "200k";
		int k = 10;
		int m = 4;
		
		long start = System.currentTimeMillis();

		service.showAnonymizedTrajectory(experiment, k, m);
		
		long elapsed = System.currentTimeMillis() - start;
		System.out.println("tempo total:" + elapsed + " milisegundos");
	}
	
//	@Test
	public void mainTest() throws Exception {
		Set<PointOfTrajectory> points = new HashSet<PointOfTrajectory>();
		
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < listOfFiles.length; i++) {
			try	{
				List<String> all = Files.readAllLines(listOfFiles[i].toPath(),  Charsets.UTF_8);
				for(String s : all){
					List<String> splited = Arrays.asList(s.split(","));
					PointOfTrajectory point = new PointOfTrajectory();
					point.setTaxiId(Integer.valueOf(splited.get(0)));
//					point.setDatetime((Date)r.getObject(2));
//					point.setLongitude(Double.valueOf(((BigDecimal)r.getObject(3)).doubleValue()));
//					point.setLatitude(Double.valueOf(((BigDecimal)r.getObject(4)).doubleValue()));
//					point.setGeom((String)r.getObject(5));
					points.add(point);
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		
		long elapsed = System.currentTimeMillis() - start;
		System.out.println("tempo total:" + elapsed + " milisegundos");
		System.out.println("tamanho: " + points.size());
	}
	
//	@Test
	public void areaPoligonTest1Ok() throws Exception
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
		
//		PointOfTrajectory p3 = new PointOfTrajectory();
//		p3.setLongitude(1.0);
//		p3.setLatitude(2.0);
//		p3.setLongitudeNn(2.0);
//		p3.setLatitudeNn(2.0);
//		
//		PointOfTrajectory p4 = new PointOfTrajectory();
//		p4.setLongitude(1.0);
//		p4.setLatitude(3.0);
//		p4.setLongitudeNn(2.0);
//		p4.setLatitudeNn(3.0);
		
		List<PointOfTrajectory> l = new ArrayList<PointOfTrajectory>();
		l.add(p1);
		l.add(p2);
//		l.add(p3);
//		l.add(p4);
		
		System.out.println(service.calculateInformationLoss(l));
		
	}
	
//	@Test
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
		
		System.out.println(service.calculateInformationLoss(l));
		
	}
	
}
