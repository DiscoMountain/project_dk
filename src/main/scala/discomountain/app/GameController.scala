package discomountain.app

import org.scalatra._
import scalate.ScalateSupport
import org.scalatra.atmosphere._
import org.scalatra.json.{JValueResult, JacksonJsonSupport}
import org.json4s._
import JsonDSL._
import java.util.Date
import java.text.SimpleDateFormat
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.None
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}
import scala.collection.mutable
import org.json4s.JsonAST.JValue

class GameController extends ScalatraServlet with ScalateSupport with JValueResult
with JacksonJsonSupport with SessionSupport with AtmosphereSupport {

  protected implicit val jsonFormats: Formats = DefaultFormats

  atmosphere("/game") {
    new AtmosphereClient {
      def receive: AtmoReceive = {
        case Connected => {
          println("Client %s is connected" format uuid)
        }
        case Disconnected(ClientDisconnected, _) =>
          println("Client disconnected from server %s" format uuid)
        case Disconnected(ServerDisconnected, _) =>
          println("Server disconnected the client %s" format uuid)
        case Error(Some(error)) =>
        case TextMessage(text) => send("ECHO (text): " + text)
        case JsonMessage(JObject(JField("command", JString("getInitialState")) :: fields)) => {
          println("Client %s is requesting initial state" format uuid)
          send(handleInitialState(fields))
        }
        case JsonMessage(JObject(JField("command", JString("getObjectData")) :: fields)) =>
          send(getObjectData(fields.head._2.asInstanceOf[JObject]))
        case JsonMessage(JObject(JField("command", JString("getPlayerPosition")) :: fields)) =>
          send(getPlayerPosition)
        case JsonMessage(json) =>
          println("not supported : " + json)
          send("ECHO (json): " + json)
      }
    }
  }

  def handleInitialState(fields: List[(String, org.json4s.JsonAST.JValue)]) = {
    implicit val formats = Serialization.formats(NoTypeHints)

    val solarSystemData: String = GameManager.getCurrentSystem("1")
    write(new ResponseObject("initialState", new RawData(solarSystemData)))

  }

  def getObjectData(fields: JObject) = {
    write(new ResponseObject("objectData", new RawData(GameManager.getObjectData(fields.obj))))
  }

  def getPlayerPosition() = {
    write(new ResponseObject("playerPosition", new RawData(GameManager.getPlayerPosition("Alpha"))))
  }

  error {
    case t: Throwable => t.printStackTrace()
  }

  notFound {
    // remove content type in case it was set through an action
    contentType = null
    // Try to render a ScalateTemplate if no route matched
    findTemplate(requestPath) map {
      path =>
        contentType = "text/html"
        layoutTemplate(path)
    } orElse serveStaticResource() getOrElse resourceNotFound()
  }

}