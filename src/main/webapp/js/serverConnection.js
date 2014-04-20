var dkConn = (function() {
	var socket = atmosphere;
	var request = new atmosphere.AtmosphereRequest();
	var gameCallback;

	var request = {
		url : '/game',
		contentType : 'application/json',
		logLevel : 'debug',
		transport : 'websocket',
		fallbackTransport : 'long-polling'
	};
	request.onOpen = function(response) {
		console.log('Atmosphere connected using ' + response.transport);
		game.connected();

	};
	
	request.onMessage = function(rs) {
	    console.log(rs)
		game.dataReceived(JSON.parse(rs.responseBody))
	}

    function getInitialState(id) {
        subSocket.push('{"command" : "getInitialState", "id" : '+ id + '}');
    }

    function getPlayerPosition() {
        subSocket.push('{"command" : "getPlayerPosition"}');
    }

    function getLocationData(location) {
        var command = new Object();
        command.command = "getObjectData";
        command.object = location;
        console.log(JSON.stringify(command));
        subSocket.push(JSON.stringify(command));
    }

	var subSocket;

	return {
		connect : function(game) {
			subSocket = socket.subscribe(request);
			gameCallback = game;
		},
		getInitialState : getInitialState,
		getPlayerPosition : getPlayerPosition,
		getLocationData : getLocationData
	}
}());
