var game;

(function() {

	var width = 500;
	var height = 500;

	var currentStarSystem = {};

	function Game() {
		console.log('game created');
	}

	Game.prototype.dataReceived = function(data) {
		console.log('data received' + data);
		this.setCurrentStarSystem(data);
	}

	Game.prototype.connect = function() {
		serverConnection.connect(game);
	}

	Game.prototype.connected = function() {
		serverConnection.send('{"command" : "getInitialState", "id" : "1"}');
	}

	Game.prototype.setCurrentStarSystem = function(starSystem) {
		currentStarSystem = starSystem;
		this.drawStarMap();
	}

	Game.prototype.getStarMap = function() {

		return currentStarSystem;
	}

	Game.prototype.drawStarMap = function() {
		var svgContainer = d3.select("body").append("svg").attr("width", width)
				.attr("height", height);
		svgContainer.append("circle").attr("cx", width / 2).attr("cy",
				height / 2).attr("r", currentStarSystem.sun.radius).attr(
				"fill", "yellow");
		var planets = currentStarSystem.sun.planets;
		for (var i = 0; i < planets.length; i++) {
			var distance = planets[i].distance;
			svgContainer.append("circle").attr("cx", width / 2 + distance)
					.attr("cy", height / 2).attr("r",
							planets[i].spaceObject.radius).attr("fill", "blue");
		}
	}

	$(document).ready(function() {
		game = new Game();
		game.connect();
	});

})();