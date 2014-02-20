import uuid
from flask import Flask
from flask.ext import restful

app = Flask(__name__)
api = restful.Api(app)

games = {}

class GamesList(restful.Resource):
    def get(self, user_id):
        return {user_id : games[user_id]}
    def put(self, user_id):
        game_id = str(uuid.uuid1())
        games[user_id] = str(uuid.uuid1())
        return {user_id : games[user_id]}



api.add_resource(GamesList, '/<string:user_id>')

if __name__ == '__main__':
    app.run(debug=True)