function getStarMap() {
	var data = [ {
		name : 'a1',
		size : 30
	}, {
		name : 'a2',
		size : 40
	} ];

	return data;
}

function drawPlanets(planets) {
	d3.select("body").selectAll("p").data(planets).append("p").text(
			function(d) {
				return "test" + d;
			});
}

function connectToServer() {
	// TODO 
}

function start() {
	connectToServer();
	
	var svgContainer = d3.select("body").append("svg").attr("width", 300).attr(
			"height", 300);

	var starMap = getStarMap();
	for (var i = 0; i < starMap.length; i++) {
		svgContainer.append("circle").attr("cx", 100 + 50 * i).attr("cy",
				100 + 50 * i).attr("r", starMap[i].size);
	}

}