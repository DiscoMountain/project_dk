package discomountain.app


object GameManager {

  def getCurrentSystem(id: String): String = {
    DataManager.getSolarSystem(id)
  }

  def getObjectData(id: String) = {
    val parts = id.split(',')
    parts.length match {
      case 2 => DataManager.getSunData(parts(0), parts(1))
      case 3 => DataManager.getPlanetData(parts(0), parts(2))
      case 4 => DataManager.getMoonData(parts(0), parts(1), parts(2))
    }

  }

  def getPlayerPosition(name: String): String = {
    DataManager.getPlayerPosition(name)
  }
}
