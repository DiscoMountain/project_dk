package discomountain.app


trait ResponseBody extends Serializable

case class ResponseObject(head: String, body: ResponseBody)


/**
 * Planetary objects related
 */
trait SpaceObject extends ResponseBody

case class Moon(name: String, radius: Int) extends SpaceObject

case class Planet(name: String, radius: Int, moons: List[ObjectDistance]) extends SpaceObject

case class Sun(name: String, radius: Int, planets: List[ObjectDistance]) extends SpaceObject

case class SolarSystem(name: String, sun: Sun) extends SpaceObject

case class ObjectDistance(spaceObject: SpaceObject, distance: Int)

/**
 *
 * Data information related
 */
case class RawData(data: String) extends ResponseBody




