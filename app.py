from flask import *
import flask
from flask import render_template
import os
import sys
app = Flask(__name__)

app.debug = True

@app.route('/')
def hello_world():
    return 'Hello World!'
@app.route('/v1/game', methods=['GET', 'POST'])
def game():
    if request.method == 'GET':
        return 'game!'
    else:
        return 'new game!'

@app.route('/v1/game/<gid>')
def new_game(gid):
    return 'new game %s!' % gid


if __name__ == '__main__':
    app.run()
