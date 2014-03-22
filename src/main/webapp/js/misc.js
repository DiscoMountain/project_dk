function Point(x, y) {
	this.x = x;
	this.y = y;
}

function getOrbitPoint(distance) {
    if (typeof(distance) == 'undefined') distance = 1;
    var angle = Math.random() * 2 * Math.PI;
    return new Point(Math.cos(angle) * distance , Math.sin(angle) * distance)

}

function SelectedObject(solarSystem, object) {
    this.solarSystem = solarSystem;
    this.object = object;
}