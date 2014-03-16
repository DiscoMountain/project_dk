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
		this.drawOrbitingObject(new Point(width / 2, height / 2), 
				planets, svgContainer);
	}

	Game.prototype.drawOrbitingObject = function(center, orbiting,
			cont) {
		for (var i = 0; i < orbiting.length; i++) {
			var orbit = getOrbitPoint();
			var distX = orbiting[i].distance * orbit.x;
			var distY = orbiting[i].distance * orbit.y;
			cont.append("circle").attr("cx", center.x + distX).attr("cy",
					center.y + distY).attr("r", orbiting[i].spaceObject.radius).attr(
					"fill", "blue");
		}
	}
	
	function getOrbitPoint() {
		var angle = Math.random() * 2 * Math.PI;
		return new Point(Math.cos(angle), Math.sin(angle))
		
	}

	$(document).ready(function() {
		game = new Game();
		game.connect();
	});

})();