package br.ufc.tranon.service;

import br.ufc.tranon.entity.PointOfTrajectory;
import java.util.List;

public abstract interface TrajectoryService
{
  public abstract List<PointOfTrajectory> showOriginalPoints(Integer paramInteger, String paramString1, String paramString2)
    throws Exception;
  
  public abstract String showOriginalTrajectory(Integer paramInteger, String paramString1, String paramString2)
    throws Exception;
  
  public abstract List<PointOfTrajectory> showRoadNetwork()
    throws Exception;
}
