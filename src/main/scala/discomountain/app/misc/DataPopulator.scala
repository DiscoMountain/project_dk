package discomountain.app.misc


import com.mongodb.casbah.Imports._

/**
 * Can be used to populate the database with the start data
 */
object DataPopulator extends App {

  val mongoClient = MongoClient("localhost", 27017)("DiscoKings")

  mongoClient.getCollection("solarsystems").drop()
  mongoClient.getCollection("players").drop()

  addSolarSystem()
  addUser()

  def addSolarSystem() = {
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

  def addUser() = {
    val startPosition = MongoDBObject("solarsystem" -> "xz",
      "planet" -> "p2")

    val defaultUser = MongoDBObject("name" -> "Alpha",
      "startPosition" -> startPosition)

    mongoClient.getCollection("players").insert(defaultUser)
  }

}
