package br.ufc.tranon.filter;

import br.ufc.tranon.entity.PointOfTrajectory;

public class PointOfTrajectoryFilters {

	public static Filter<PointOfTrajectory, Long> taxiIdFilter() {
		
		Filter<PointOfTrajectory,Long> filter = new Filter<PointOfTrajectory,Long>() {
			public boolean isMatched(PointOfTrajectory object, Long nn) {
				return object.getNn().equals(nn);
			}
		};
		
		return filter;
		
	}
	
}
