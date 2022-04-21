package fileIOComponent.fileIO_JSON_Impl

import fileIOComponent.FileIOInterface
import model.BoardInterface
import model.gameComponents.Player
import model.gameComponents.MisterX
import model.gameComponents.Detective
import model.gameComponents.Ticket
import model.gameComponents.Cell
import model.gameComponents.Board
import model.gameComponents.GameInformation
import play.api.libs.json.*

import java.io.*
import scala.collection.mutable.ListBuffer
import scala.io.*

class FileIOJSON extends FileIOInterface :

	override def load(): Board =

		val source = Source.fromFile("board.json")
		val data: String = source.getLines.mkString
		val json: JsValue = Json.parse(data)
		source.close()


		val info = json \ "gameInformation"
		val travelLog = ListBuffer[Int]()
		val travelLogString = info.get("travelLog").toString()
		("""\d+""".r findAllIn travelLogString).foreach(x => travelLog += x.toInt)
		val loadedGameInfo = GameInformation(
			travelLog,
			info.get("revealCounter").toString().toInt,
			info.get("currentPlayer").toString().toInt
		)

		val playerNumber = info.get("playerNumber").toString().toInt
		val players = json \ "players"
		val loadedPlayers: Vector[Player] =
			(0 until playerNumber).map(i =>
				if i == 0 then
					MisterX((players(i) \ "name").as[String],
						Cell((players(i) \ "cell").as[Int]),
						Ticket((players(i) \ "ticket").get("taxi").toString().toInt,
							(players(i) \ "ticket").get("bus").toString().toInt,
							(players(i) \ "ticket").get("subway").toString().toInt,
							(players(i) \ "ticket").get("black").toString().toInt)
					)
				else
					Detective((players(i) \ "name").as[String],
						Cell((players(i) \ "cell").as[Int]),
						Ticket((players(i) \ "ticket").get("taxi").toString().toInt,
							(players(i) \ "ticket").get("bus").toString().toInt,
							(players(i) \ "ticket").get("subway").toString().toInt,
							(players(i) \ "ticket").get("black").toString().toInt)
					)
			).toVector

		Board(loadedPlayers, loadedGameInfo)


	override def save(board: BoardInterface): Unit =
		val pw = new PrintWriter(new File("board.json"))
		pw.write(Json.prettyPrint(boardToJson(board)))
		pw.close()

	def boardToJson(board: BoardInterface): JsValue =
		Json.obj(
		"gameInformation" -> Json.obj(
			"currentPlayer" -> board.gameInfo.currentPlayer,
			"travelLog" -> board.gameInfo.travelLog.mkString(" "),
			"revealCounter" -> board.gameInfo.revealCounter,
			"playerNumber" -> board.player.size
		), 
			"players" -> Json.toJson(
				for player <- board.player
					yield Json.obj(
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
