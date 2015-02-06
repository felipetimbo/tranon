// Global vars
var MSG_FIELDS_REQUIRED = 'All fields are required.';
var MSG_ERROR = 'Error on Request.';

var originalPointsLayerGroup;
var originalPointsLayer = [];

var originalTrajectoryLayerGroup;
var originalTrajectoryLayer = [];

var pointIconBlue = L.icon({
    iconUrl: 'images/blue_point.png',

    iconSize:     [10, 10], // size of the icon
    popupAnchor:  [11, -30] // point from which the popup should open relative to the iconAnchor
});

var pointIconRed = L.icon({
    iconUrl: 'images/red_point.png',

    iconSize:     [10, 10], // size of the icon
    popupAnchor:  [11, -30] // point from which the popup should open relative to the iconAnchor
});

var map = L.map('map').setView([39.913889, 116.391667], 11);

L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
    maxZoom: 18
}).addTo(map);


$(document).ready(function(){
	
	/*
	L.marker([39.913889, 116.391667], {icon: pointIconRed}).addTo(map);
	/*
	L.marker([39.91428,116.39165], {icon: pointIconBlue}).addTo(map);
	L.marker([39.91423,116.39193], {icon: pointIconBlue}).addTo(map);
	L.marker([39.91423,116.39193], {icon: pointIconBlue}).addTo(map);
	L.marker([39.9134,116.39182], {icon: pointIconBlue}).addTo(map);
	L.marker([39.91443,116.39167], {icon: pointIconBlue}).addTo(map);
	L.marker([39.9134,116.39192], {icon: pointIconBlue}).addTo(map);
	L.marker([39.9134,116.39192], {icon: pointIconBlue}).addTo(map);
	L.marker([39.91342,116.39205], {icon: pointIconBlue}).addTo(map);
	L.marker([39.91342,116.39205], {icon: pointIconBlue}).addTo(map);
	L.marker([39.91453,116.39168], {icon: pointIconBlue}).addTo(map);
	/*
	L.marker([ 40.02613,116.45628 ], {icon: pointIconRed}).addTo(map);
	L.marker([ 40.0415,116.47498 ], {icon: pointIconRed}).addTo(map);
	L.marker([ 40.04468,116.47617 ], {icon: pointIconRed}).addTo(map);
	L.marker([ 40.05008,116.47655 ], {icon: pointIconRed}).addTo(map);
	L.marker([ 40.06163,116.47612 ], {icon: pointIconRed}).addTo(map);
	L.marker([ 40.07665,116.47558 ], {icon: pointIconRed}).addTo(map);
	L.marker([ 40.08968,116.47515 ], {icon: pointIconRed}).addTo(map);
	L.marker([ 39.97843,116.4728 ], {icon: pointIconRed}).addTo(map);
	L.marker([ 39.97958,116.47408 ], {icon: pointIconRed}).addTo(map);
	L.marker([ 40.01008,116.50787 ], {icon: pointIconRed}).addTo(map);
	
	
	L.marker([39.6849513,116.36748620000003], {icon: pointIconBlue}).addTo(map);
	
	L.marker([39.6841,116.36606], {icon: pointIconRed}).addTo(map);
	L.marker([39.68409,116.36606], {icon: pointIconRed}).addTo(map);
	L.marker([39.68409,116.36605], {icon: pointIconRed}).addTo(map);
	L.marker([39.68408,116.36611], {icon: pointIconRed}).addTo(map);
	L.marker([39.68413,116.36615], {icon: pointIconRed}).addTo(map);
	*/
});

function showOriginalPoints(){
	
	var exp = $("#experiment")[0].value;
	var taxi_id = $("#taxi_id")[0].value;
	var s_date = $("#s_date")[0].value + " " + $("#s_hour")[0].value;
	var f_date = $("#f_date")[0].value + " " + $("#f_hour")[0].value;
	
	$.ajax({
		url: 'index/showOriginalPoints',
		cache: false, 
		data: { experiment: exp,
			taxiId: taxi_id, 
			startDate: s_date,
			finalDate: f_date},
			success: function(data){
				
				for (var i=0; i < data.originalPoints.length; i++) {
					  var point = data.originalPoints[i];
					  addPointToMap(point, 'blue');
				}
				
				originalPointsLayerGroup = L.layerGroup(originalPointsLayer).addTo(map);
				
			},error: function(){
				alert(MSG_ERROR);
			}
	});
}

function showSimplifiedPoints(){
	
	var exp = $("#experiment")[0].value;
	var taxi_id = $("#taxi_id")[0].value;
	var s_date = $("#s_date")[0].value + " " + $("#s_hour")[0].value;
	var f_date = $("#f_date")[0].value + " " + $("#f_hour")[0].value;
	
	$.ajax({
		url: 'index/showSimplifiedPoints',
		cache: false, 
		data: {experiment: exp,
			taxiId: taxi_id, 
			startDate: s_date,
			finalDate: f_date},
			success: function(data){
				
				for (var i=0; i < data.simplifiedPoints.length; i++) {
					  var point = data.simplifiedPoints[i];
					  addPointToMap(point, 'red');
				}
				
				originalPointsLayerGroup = L.layerGroup(originalPointsLayer).addTo(map);
				
			},error: function(){
				alert(MSG_ERROR);
			}
	});
	
}

function showOriginalTrajectory(){
	
	var exp = $("#experiment")[0].value;
	var taxi_id = $("#taxi_id")[0].value;
	var s_date = $("#s_date")[0].value + " " + $("#s_hour")[0].value;
	var f_date = $("#f_date")[0].value + " " + $("#f_hour")[0].value;
	
	$.ajax({
		url: 'index/showOriginalTrajectory',
		cache: false, 
		data: {experiment: exp,
			taxiId: taxi_id, 
			startDate: s_date,
			finalDate: f_date},
			success: function(data){
				
				var trajectory = data.originalTrajectory;
				addTrajectoryToMap(trajectory, 'blue');
				
				originalTrajectoryLayerGroup = L.layerGroup(originalTrajectoryLayer).addTo(map);
				
			},error: function(){
				alert(MSG_ERROR);
			}
	});
}

function showSimplifiedTrajectory(){
	
	var exp = $("#experiment")[0].value;
	var taxi_id = $("#taxi_id")[0].value;
	var s_date = $("#s_date")[0].value + " " + $("#s_hour")[0].value;
	var f_date = $("#f_date")[0].value + " " + $("#f_hour")[0].value;
	
	$.ajax({
		url: 'index/showSimplifiedTrajectory',
		cache: false, 
		data: {experiment: exp,
			taxiId: taxi_id, 
			startDate: s_date,
			finalDate: f_date},
			success: function(data){
				
				var trajectory = data.simplifiedTrajectory;
				addTrajectoryToMap(trajectory, 'red');
				
				originalTrajectoryLayerGroup = L.layerGroup(originalTrajectoryLayer).addTo(map);
				
			},error: function(){
				alert(MSG_ERROR);
			}
	});
}

function showRoadNetwork(){
	
	$.ajax({
		url: 'index/showRoadNetwork',
		cache: false, 
		success: function(data){
				
			for (var i=0; i < data.roadNetwork.length; i++) {
				  var point = data.roadNetwork[i];
				  addPointToMap(point);
			}
			
			originalPointsLayerGroup = L.layerGroup(originalPointsLayer).addTo(map);
			
		},error: function(){
			alert(MSG_ERROR);
		}
	});
	
}

function addPointToMap(point, color){
	
	geo = new Object();
	geo = eval("("+point.geom+")");
	
	var pointFeature = {
		    "type": "Feature",
		    "properties": {
		    	"taxi_id": point.taxiId
		    },
		    "geometry": geo
		};
	
	var elementLayer = L.geoJson(pointFeature, {
		 pointToLayer: function (feature, latlng) {
//			 	var coord = [latlng.lat, latlng.lng]; 
//			    var lnglat = L.GeoJSON.coordsToLatLng(coord, false);
		        return L.marker(latlng, (color == 'blue') ? {icon: pointIconBlue} : {icon: pointIconRed}  );
		 }
//	,
//		 onEachFeature: caracteristicasPonto
	});
	
	originalPointsLayer.push(elementLayer);
}

function addTrajectoryToMap(trajectory, color){
	
	geo = new Object();
	geo = eval("("+trajectory+")");
	
	var trajectoryFeature = {
		    "type": "Feature",
		    "geometry": geo
		};
	
	var elementLayer = L.geoJson(trajectoryFeature, (color == 'blue') ? {color: 'blue'} : {color: 'red'});
	
	originalTrajectoryLayer.push(elementLayer);
}

function clearMap() {
	
	if(typeof(originalPointsLayerGroup) != "undefined"){
		originalPointsLayerGroup.clearLayers();
	}
	if(typeof(originalTrajectoryLayerGroup) != "undefined"){
		originalTrajectoryLayerGroup.clearLayers();
	}
	
	originalPointsLayer = [];
	originalTrajectoryLayer = [];

}

//input only numbers
$("#taxi_id").keyup(function(){
    $(this).val($(this).val().replace(/[^\d]/,''));
});
