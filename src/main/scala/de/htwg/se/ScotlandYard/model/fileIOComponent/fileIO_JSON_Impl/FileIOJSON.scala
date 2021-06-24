package de.htwg.se.ScotlandYard.model.fileIOComponent.fileIO_JSON_Impl

import de.htwg.se.ScotlandYard.ScotlandYard.controller
import de.htwg.se.ScotlandYard.controller.ControllerInterface
import de.htwg.se.ScotlandYard.model.BoardInterface
import de.htwg.se.ScotlandYard.model.fileIOComponent.FileIOInterface
import play.api.libs.json._

import java.io._
/*
class FileIOJSON extends FileIOInterface {
  override def load: ControllerInterface = {
    ???
  }

  override def save(controller: ControllerInterface): Unit = {
    val pw = new PrintWriter(new File("controller.json"))
    pw.write(Json.prettyPrint(boardToJson(controller)))
    pw.close
  }

  def boardToJson(controller: ControllerInterface):JsValue = {
    val p1 = controller.board.player(0)
    val p2 = controller.board.player(1)
    Json.obj(
      "player" -> Json.obj(
          "player1" -> Json.obj(
            "name" -> p1.name,
            "cell" -> p1.cell.toString,
            "ticket" -> Json.obj(
              "taxi" -> p1.ticket.taxi.toString,
              "bus" -> p1.ticket.bus.toString,
              "subway" -> p1.ticket.subway.toString,
              "black" -> p1.ticket.black.toString,
            ),
            "typ" -> p1.typ.toString
          ),
        "player2" -> Json.obj(
          "name" -> p2.name,
          "cell" -> p2.cell.toString,
          "ticket" -> Json.obj(
            "taxi" -> p2.ticket.taxi.toString,
            "bus" -> p2.ticket.bus.toString,
            "subway" -> p2.ticket.subway.toString,
            "black" -> p2.ticket.black.toString,
          ),
          "typ" -> p2.typ.toString
        )
      )
    )
  }
}
*/