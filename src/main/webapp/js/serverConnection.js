var serverConnection = (function() {
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

	};

	var subSocket;

	return {
		connect : function(game) {
			subSocket = socket.subscribe(request);
			gameCallback = game;
		},
		send : function(data) {
			subSocket.push(data);
		}
	}
}());
