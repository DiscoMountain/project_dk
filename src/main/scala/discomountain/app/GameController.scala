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

class GameController extends ScalatraServlet with ScalateSupport with 
	JValueResult with JacksonJsonSupport with SessionSupport with AtmosphereSupport {
  
  protected implicit val jsonFormats: Formats = DefaultFormats.withBigDecimal
  
  atmosphere("/game") {
    new AtmosphereClient {
      def receive = {
        case Connected => println("client connected")
        case Disconnected(disconnector, Some(error)) =>  println("client disconnected" )
        case _ => println("not handled yet")
      }
    }
  }

}