package discomountain.app

import com.mongodb.casbah.Imports._



object DataManager {

  private val mongoClient = MongoClient("localhost", 27017)("DiscoKings")

  def getSolarSystem(id: String): String = {
    // Always return the same system for now
    val mongoSystem = mongoClient.getCollection("solarsystems").findOne()

    mongoSystem.toString
  }

  def getPlayerPosition(name: String): String = {
    val query = MongoDBObject("name" -> name)
    mongoClient.getCollection("players").findOne(query).get("position").toString
  }

  def getSunData(solarSystemId: String, sunId: String) = {
    s"This is the sun in $solarSystemId.\n" +
      s"The sun is called $sunId."
  }

  def getPlanetData(solarSystemId: String, planetId: String) = {
    s"This is the planet in $solarSystemId.\n" +
      s"The planet is called $planetId."
  }

  def getMoonData(solarSystemId: String, planetId: String, moonId: String) = {
    s"This is a moon in $solarSystemId, orbiting planet $planetId.\n" +
      s"The moon is called $moonId"
  }
}
