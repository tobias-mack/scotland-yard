package de.htwg.se.ScotlandYard.model.fileIOComponent.fileIO_JSON_Impl

import de.htwg.se.ScotlandYard.controller.ControllerInterface
import de.htwg.se.ScotlandYard.model.fileIOComponent.FileIOInterface
import de.htwg.se.ScotlandYard.model.gameComponents.{Cell, Detective, MisterX, Player, Ticket}
import play.api.libs.json._

import scala.io._
import java.io._

class FileIOJSON extends FileIOInterface :
  override def load(controller: ControllerInterface): Vector[Player] =
    val source: String = Source.fromFile("controller.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val info = json \ "gameInformation"
    val travelLog = info.get("travelLog").toString()
    ("""\d+""".r findAllIn travelLog).foreach(x => controller.travelLog += x.toInt)

    controller.order = info.get("currentPlayer").toString().toInt
    controller.revealCounter = info.get("revealCounter").toString().toInt
    controller.playerNumber = info.get("playerNumber").toString().toInt
    val players = json \ "players"
    val ret: Vector[Player] =
      (0 until controller.playerNumber).map(i =>
        if i == 0 then
          MisterX((players(i) \ "name").as[String],
            Cell((players(i) \ "cell").as[Int]),
            Ticket((players(i) \ "ticket").get("taxi").toString().toInt,
              (players(i) \ "ticket").get("bus").toString().toInt,
              (players(i) \ "ticket").get("subway").toString().toInt,
              (players(i) \ "ticket").get("black").toString().toInt) //,
            //(players(i)\"typ").as[String].toInt
          )
        else
          Detective((players(i) \ "name").as[String],
            Cell((players(i) \ "cell").as[Int]),
            Ticket((players(i) \ "ticket").get("taxi").toString().toInt,
              (players(i) \ "ticket").get("bus").toString().toInt,
              (players(i) \ "ticket").get("subway").toString().toInt,
              (players(i) \ "ticket").get("black").toString().toInt) //,
            //(players(i)\"typ").as[Int]
          )
      ).toVector
    ret


  override def save(controller: ControllerInterface): Unit =
    val pw = new PrintWriter(new File("controller.json"))
    pw.write(Json.prettyPrint(boardToJson(controller)))
    pw.close()

  def boardToJson(controller: ControllerInterface): JsValue =
    Json.obj(
      "gameInformation" -> Json.obj(
        "currentPlayer" -> controller.order,
        "travelLog" -> controller.travelLog.mkString(" "),
        "revealCounter" -> controller.revealCounter,
        "playerNumber" -> controller.playerAdded
      ),
      "players" -> Json.toJson(
        controller.board.player.map(player => Json.obj(
          "name" -> player.name,
          "cell" -> player.cell.number,
          "typ" -> player.typ,
          "ticket" -> Json.obj(
            "taxi" -> player.ticket.taxi,
            "bus" -> player.ticket.bus,
            "subway" -> player.ticket.subway,
            "black" -> player.ticket.black,
          )
        ))
      )
    )