function Point(x, y) {
	this.x = x;
	this.y = y;
}

function getOrbitPoint() {
    var angle = Math.random() * 2 * Math.PI;
    return new Point(Math.cos(angle), Math.sin(angle))

}