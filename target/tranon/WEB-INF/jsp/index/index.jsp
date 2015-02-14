<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
		<title>Trajectory Anonymity</title>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
		<script src="<c:url value="/kickstart/js/kickstart.js"/>"></script> 
		<link rel="stylesheet" href="<c:url value="/kickstart/css/kickstart.css"/>" media="all" /> 
		
		<link href="<c:url value="/css/leaflet.css"/>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/css/tranon.css"/>" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="<c:url value="/js/leaflet.js"/>"></script>

	</head>
	<body>
		<select id="experiment">
			<option value="">-- Experiment --</option>
			<option value="100k">100k</option>
			<option value="200k">200k</option>
			<option value="400k">400k</option>
			<option value="800k">800k</option>
		</select>
		<input id="taxi_id" type="text" placeholder="Taxi ID" style="width:65px;" maxlength="5" />
		<select id="s_date">
			<option value="">-- Start Date --</option>
			<option value="2008-02-02">February, 2</option>
			<option value="2008-02-03">February, 3</option>
			<option value="2008-02-04">February, 4</option>
			<option value="2008-02-05">February, 5</option>
			<option value="2008-02-06">February, 6</option>
			<option value="2008-02-07">February, 7</option>
			<option value="2008-02-08">February, 8</option>
		</select>
		<input id="s_hour" type="text" placeholder="hh:mm" style="width:60px;" maxlength="5" />
		<select id="f_date">
			<option value="">-- Final Date --</option>
			<option value="2008-02-02">February, 2</option>
			<option value="2008-02-03">February, 3</option>
			<option value="2008-02-04">February, 4</option>
			<option value="2008-02-05">February, 5</option>
			<option value="2008-02-06">February, 6</option>
			<option value="2008-02-07">February, 7</option>
			<option value="2008-02-08">February, 8</option>
		</select>
		<input id="f_hour" type="text" placeholder="hh:mm" style="width:60px;" maxlength="5" />
		<button id="original_points" class="small" onclick="showOriginalPoints();">Or. Points</button>
		<button id="original_trajectory" class="small" onclick="showOriginalTrajectory();">Or. Trajectory</button>
		<button id="simplified_points" class="small" onclick="showSimplifiedPoints();">S. Points</button>
		<button id="simplified_trajectory" class="small" onclick="showSimplifiedTrajectory();">S. Trajectory</button>
		<button class="small">Anonymized</button>
		<button id="clear" class="small" onclick="clearMap();">Clear</button>
		<button id="original" class="small" onclick="showRoadNetwork();">Road Network</button>
		
		<div id="map"></div>
		<script src="<c:url value="/js/tranon.js"/>"></script>

	</body>
</html>