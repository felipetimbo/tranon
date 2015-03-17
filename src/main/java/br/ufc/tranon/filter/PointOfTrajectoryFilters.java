package br.ufc.tranon.filter;

import br.ufc.tranon.entity.RoadNetworkPoint;

public class PointOfTrajectoryFilters {

	public static Filter<RoadNetworkPoint, Long> nearestNeighborFilter() {
		
		Filter<RoadNetworkPoint,Long> filter = new Filter<RoadNetworkPoint,Long>() {
			public boolean isMatched(RoadNetworkPoint point, Long nnId) {
				return point.getId().equals(nnId);
			}
		};
		
		return filter;
		
	}
	
}
