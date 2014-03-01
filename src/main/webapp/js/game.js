var game;

(function() {
	function Game() {
		console.log('game created');
	}

	Game.prototype.dataReceived = function(data) {
		console.log('data received' + data);
	}

	Game.prototype.connect = function() {
		serverConnection.connect(game);
	}

	Game.prototype.getStarMap = function() {
		var data = [ {
			name : 'a1',
			size : 30
		}, {
			name : 'a2',
			size : 40
		} ];

		return data;
	}

	Game.prototype.drawStarMap = function() {
		var svgContainer = d3.select("body").append("svg").attr("width", 300)
				.attr("height", 300);
		var starMap = this.getStarMap();
		for (var i = 0; i < starMap.length; i++) {
			svgContainer.append("circle").attr("cx", 100 + 50 * i).attr("cy",
					100 + 50 * i).attr("r", starMap[i].size);
		}
	}

	$(document).ready(function(){
		game = new Game();
		game.connect();
		game.drawStarMap();	
	});
	
})();