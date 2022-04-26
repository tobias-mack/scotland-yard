package fileIOComponent.api

import com.google.inject.{Guice, Inject}

import java.io._
import play.api.libs.json.{JsValue, Json}
import scala.io.Source

object APIController {

  def load(): String = {
    val file = scala.io.Source.fromFile("board.json")
    try file.mkString finally file.close()
  }

  def save(gameAsJson: String): Unit = {
    val pw = new PrintWriter(new File("." + File.separator + "game.json"))
    pw.write(gameAsJson)
    pw.close
  }

}
