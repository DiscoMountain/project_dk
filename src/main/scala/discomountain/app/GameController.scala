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

class GameController extends ScalatraServlet with ScalateSupport with JValueResult with JacksonJsonSupport with SessionSupport with AtmosphereSupport {

  protected implicit val jsonFormats: Formats = DefaultFormats

  atmosphere("/game") {
    new AtmosphereClient {
      def receive: AtmoReceive = {
        case Connected => println("client connected")
        case Disconnected(ClientDisconnected, _) =>
          println("Client disconnected from server %s" format uuid)

        case Disconnected(ServerDisconnected, _) =>
          println("Server disconnected the client %s" format uuid)
        case Error(Some(error)) =>
        case TextMessage(text) => send("ECHO: " + text)
        case JsonMessage(json) => send("ECHO: " + json)
      }
    }
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