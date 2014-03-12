package discomountain.app


trait SpaceObject
case class Moon(name: String, radius: Int) extends Serializable with SpaceObject
case class Planet(name: String, radius: Int, moons: List[ObjectDistance]) extends Serializable with SpaceObject
case class Sun(name: String, radius: Int, planets: List[ObjectDistance]) extends Serializable with SpaceObject
case class SolarSystem(name: String, sun: Sun) extends Serializable with SpaceObject

case class ObjectDistance(spaceObject: SpaceObject, distance: Int)


