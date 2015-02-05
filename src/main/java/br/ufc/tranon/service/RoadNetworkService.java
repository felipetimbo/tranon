package br.ufc.tranon.service;

import br.ufc.tranon.entity.PointOfTrajectory;
import java.util.List;

public abstract interface RoadNetworkService
{
  public List<PointOfTrajectory> showSimplifiedPoints(Integer taxiId, String startDate, String finalDate)
    throws Exception;
  
  public String showSimplifiedTrajectory(Integer taxiId, String startDate, String finalDate)
    throws Exception;
}