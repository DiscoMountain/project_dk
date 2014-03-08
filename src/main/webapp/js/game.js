var game;

(function() {
	
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
		var svgContainer = d3.select("body").append("svg").attr("width", 300)
				.attr("height", 300);
		svgContainer.append("circle").attr("cx", 150).attr("cy", 150).attr("r", currentStarSystem.sun.radius);
		var planets = currentStarSystem.sun.planets;
		for (var i = 0; i < planets.length; i++) {
			var distance = planets[i].distance;
			svgContainer.append("circle").attr("cx", 150 + distance).attr("cy",
					150).attr("r", planets[i].starObject.radius);
		}
	}

	$(document).ready(function(){
		game = new Game();
		game.connect();
	});
	
})();