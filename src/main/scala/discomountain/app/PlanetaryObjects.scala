package discomountain.app


trait PlanetaryObject
case class Moon(name: String, radius: Int) extends Serializable with PlanetaryObject
case class Planet(name: String, radius: Int, moons: List[ObjectDistance]) extends Serializable with PlanetaryObject
case class Sun(name: String, radius: Int, planets: List[ObjectDistance]) extends Serializable with PlanetaryObject
case class SolarSystem(name: String, sun: Sun) extends Serializable with PlanetaryObject

case class ObjectDistance(starObject: PlanetaryObject, distance: Int)


