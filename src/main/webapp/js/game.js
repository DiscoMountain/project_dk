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
		console.log(currentStarSystem)
		var planets = currentStarSystem.sun.planets;
		console.log(planets);
		for (var i = 0; i < planets.length; i++) {
			svgContainer.append("circle").attr("cx", 100 + 50 * i).attr("cy",
					100 + 50 * i).attr("r", planets[i].starObject.radius);
		}
	}

	$(document).ready(function(){
		game = new Game();
		game.connect();
		//game.drawStarMap();	
	});
	
})();