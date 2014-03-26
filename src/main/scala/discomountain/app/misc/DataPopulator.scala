package discomountain.app.misc


import com.mongodb.casbah.Imports._
import discomountain.app.{SolarSystem, ObjectDistance, Sun, Planet}

/**
 * Can be used to populate the database with the start data
 */
object DataPopulator extends App {

  val builder = MongoDBObject.newBuilder
  val mongoClient = MongoClient("localhost", 27017)("DiscoKings")
  val planet1 = MongoDBObject("name" -> "p1",
    "radius" -> 7)

  val planet2 = MongoDBObject("name" -> "p2",
    "radius" -> 6)

  val planet3 = MongoDBObject("name" -> "p3", "radius" -> 10)

  val sun = MongoDBObject("name" -> "Sun",
    "radius" -> 30,
    "planets" -> List((planet1, 70), (planet2, 60), (planet3, 110)))
  val system = MongoDBObject("name" -> "xz",
    "sun" -> sun)
  mongoClient.getCollection("solarsystems").insert(system)

}
