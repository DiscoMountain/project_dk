var game;

(function() {

	var width = 500;
	var height = 500;

    var player = "alpha"
	var currentStarSystem = {};
	var selectedLocation;

	function Game() {
		console.log('game created');
	}

	Game.prototype.dataReceived = function(data) {
		console.log('data received' + data);
		if (data.head == 'initialState') {
		    console.log('received initial state:' + data.body);
		    this.setCurrentStarSystem(JSON.parse(data.body.data));
		} else if (data.head == 'objectData') {
            console.log('received object data: ' + data.body);
            this.setInfoText(data.body);
		} else if (data.head == 'playerPosition') {
		    console.log('received player position: ' + data.body);
		} else {
		    console.log('can not handle this data atm' + data.body);
		}
	}

	Game.prototype.connect = function() {
		dkConn.connect(game);
	}

	Game.prototype.connected = function() {
		dkConn.getInitialState(1);
		dkConn.getPlayerPosition();
	}

	Game.prototype.setCurrentStarSystem = function(starSystem) {
		currentStarSystem = starSystem;
		this.drawStarMap();
	}

	Game.prototype.getStarMap = function() {
		return currentStarSystem;
	}

	Game.prototype.drawStarMap = function() {
		var svgContainer = d3.select("#mapArea").append("svg").attr("width", width)
				.attr("height", height);
		svgContainer.append("circle").attr("cx", width / 2).attr("cy",
				height / 2).attr("r", currentStarSystem.sun.radius).attr(
				"fill", "yellow").on("click", function() {
                    selectedLocation = currentStarSystem.name + "," + currentStarSystem.sun.name;
                    fetchData();
				});
		var planets = currentStarSystem.sun.planets;
		this.drawPlanets(new Point(width / 2, height / 2),
				planets, svgContainer);
	}

	Game.prototype.drawPlanets = function(center, orbiting,
			cont) {
		for (var i = 0; i < orbiting.length; i++) {
			var orbit = getOrbitPoint(orbiting[i][1]);
			var spaceObject = orbiting[i][0];
			cont.append("circle").attr("cx", center.x + orbit.x).attr("cy",
					center.y + orbit.y).attr("r", spaceObject.radius).attr(
					"fill", "blue").on("click", createOnClickForPlanet(spaceObject));
		}
	}

	Game.prototype.setInfoText = function(infoText) {
        $("#rawData").val(infoText.data);
	}

	function createOnClickForPlanet(spaceObject) {
	    return function() {
	        selectedLocation = currentStarSystem.name + "," + currentStarSystem.sun.name +
                                 "," + spaceObject.name;
            fetchData();
	    }
	}

	function fetchData() {
        console.log(selectedLocation);
        dkConn.getLocationData(selectedLocation);
	}


	$(document).ready(function() {
		game = new Game();
		game.connect();
	});

})();