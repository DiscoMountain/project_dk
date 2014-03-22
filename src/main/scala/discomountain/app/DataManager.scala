package discomountain.app


object DataManager {

  private val planet1 = new Planet("p1", 7, Nil)
  private val planet2 = new Planet("p2", 6, Nil)
  private val sun = new Sun("sun", 30,
    ObjectDistance(planet1, 70) :: ObjectDistance(planet2, 120) :: Nil)
  private val system = new SolarSystem("xz", sun)

  def getSolarSystem(id: String) = {
    // Always return the same system for now
    system
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
