package discomountain.app

import org.scalatra._
import scalate.ScalateSupport
import org.scalatra.atmosphere._
import org.scalatra.json.{ JValueResult, JacksonJsonSupport }
import org.json4s._
import JsonDSL._
import java.util.Date
import java.text.SimpleDateFormat
import org.fusesource.scalate.Template
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.None
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}

class GameController extends ScalatraServlet with ScalateSupport with JValueResult with JacksonJsonSupport with SessionSupport with AtmosphereSupport {

  protected implicit val jsonFormats: Formats = DefaultFormats

  atmosphere("/game") {
    new AtmosphereClient {
      def receive: AtmoReceive = {
        case Connected => {
          println("client connected")
        }
        case Disconnected(ClientDisconnected, _) =>
          println("Client disconnected from server %s" format uuid)

        case Disconnected(ServerDisconnected, _) =>
          println("Server disconnected the client %s" format uuid)
        case Error(Some(error)) =>
        case TextMessage(text) => send("ECHO (text): " + text)
        case JsonMessage(JObject(JField("command", JString("getInitialState")) :: fields)) =>
          send(handleInitialState(fields))
        case JsonMessage(json) => send("ECHO (json): " + json)
      }
    }
  }

  def handleInitialState(fields: List[(String, org.json4s.JsonAST.JValue)]) = {
    implicit val formats = Serialization.formats(NoTypeHints)
    for ((s, v) <- fields) {
      println(s)
      println(v)
    }
    val planet1 = new Planet("p1", 7, Nil)
    val planet2 = new Planet("p2", 6, Nil)
    val sun = new Sun("sun", 30, ObjectDistance(planet1, 100) :: ObjectDistance(planet2, 200) :: Nil)
    val system = new SolarSystem("xz", sun)
    write(system)
  }

  error {
    case t: Throwable => t.printStackTrace()
  }

  notFound {
    // remove content type in case it was set through an action
    contentType = null
    // Try to render a ScalateTemplate if no route matched
    findTemplate(requestPath) map { path =>
      contentType = "text/html"
      layoutTemplate(path)
    } orElse serveStaticResource() getOrElse resourceNotFound()
  }

}