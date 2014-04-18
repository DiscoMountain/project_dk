package discomountain.app

import org.json4s.jackson.Serialization
import org.json4s.{DefaultFormats, Formats, NoTypeHints}
import org.json4s.JsonAST.{JString, JField}


object GameManager {
  implicit val formats = Serialization.formats(NoTypeHints)
  protected implicit val jsonFormats: Formats = DefaultFormats

  def getCurrentSystem(id: String): String = {
    DataManager.getSolarSystem(id)
  }

  def getObjectData(fields: List[(String, org.json4s.JsonAST.JValue)]) = fields match {
    case (JField("system", JString(sys)) :: JField("sun", JString(sun)) :: Nil) =>
      DataManager.getSunData(sys, sun)
    case (JField("system", JString(sys)) :: JField("planet", JString(planet)) :: Nil) =>
      DataManager.getPlanetData(sys, planet)
  }

  def getPlayerPosition(name: String): String = {
    DataManager.getPlayerPosition(name)
  }
}
