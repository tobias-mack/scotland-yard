package de.htwg.se.ScotlandYard.model.fileIOComponent.fileIO_JSON_Impl

import de.htwg.se.ScotlandYard.ScotlandYard.controller
import de.htwg.se.ScotlandYard.controller.ControllerInterface
import de.htwg.se.ScotlandYard.model.BoardInterface
import de.htwg.se.ScotlandYard.model.fileIOComponent.FileIOInterface
import de.htwg.se.ScotlandYard.model.gameComponents.{Cell, Detective, MisterX, Player, Ticket}
import play.api.libs.json._

import scala.io._
import java.io._

class FileIOJSON extends FileIOInterface {

  override def load(controller: ControllerInterface): Vector[Player] = {
    val source: String = Source.fromFile("controller.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val p1 = (json \ "player" \ "player1")
    val p2 = (json \ "player" \ "player2")

    val t1 = (json \ "player" \ "player1"  \ "ticket")
    val t2 = (json \ "player" \ "player2"  \ "ticket")

    val p1n = p1.get("name").toString()
    val p1c = Cell(p1.get("cell").toString().toInt)
    val p1t = Ticket(t1.get("taxi").toString().toInt,t1.get("bus").toString().toInt,t1.get("subway").toString().toInt,t1.get("black").toString().toInt)

    val p2n = p2.get("name").toString()
    val p2c = Cell(p2.get("cell").toString().toInt)
    val p2t = Ticket(t2.get("taxi").toString().toInt,t2.get("bus").toString().toInt,t2.get("subway").toString().toInt,t2.get("black").toString().toInt)

    val player1 = new MisterX(p1n,p1c,p1t)
    val player2 = new Detective(p2n,p2c,p2t)


    val ret: Vector[Player] = Vector[Player](player1,player2)
    ret
  }



  override def save(controller: ControllerInterface): Unit = {
    val pw = new PrintWriter(new File("controller.json"))
    pw.write(Json.prettyPrint(boardToJson(controller)))
    pw.close()
  }

  def boardToJson(controller: ControllerInterface):JsValue = {
    val p1 = controller.board.player(0)
    val p2 = controller.board.player(1)
    Json.obj(
      "player" -> Json.obj(
          "player1" -> Json.obj(
            "name" -> p1.name,
            "cell" -> p1.cell.number,
            "ticket" -> Json.obj(
              "taxi" -> p1.ticket.taxi,
              "bus" -> p1.ticket.bus,
              "subway" -> p1.ticket.subway,
              "black" -> p1.ticket.black,
            ),
            "typ" -> p1.typ
          ),
        "player2" -> Json.obj(
          "name" -> p2.name,
          "cell" -> p2.cell.number,
          "ticket" -> Json.obj(
            "taxi" -> p2.ticket.taxi,
            "bus" -> p2.ticket.bus,
            "subway" -> p2.ticket.subway,
            "black" -> p2.ticket.black,
          ),
          "typ" -> p2.typ
        )
      )
    )
  }


}