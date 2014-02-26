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
	var socket = atmosphere;
	var request = new atmosphere.AtmosphereRequest();
	
	var request = {
		url : '/game',
		contentType : 'application/json',
		logLevel : 'debug',
		transport : 'websocket',
		fallbackTransport: 'long-polling'
	};
	request.onOpen = function(response) {
        console.log('Atmosphere connected using ' + response.transport);
		
    };
    
    var subSocket = socket.subscribe(request);
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