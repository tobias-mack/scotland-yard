package fileIOComponent.databaseComponent

import akka.parboiled2.RuleTrace.Optional
import modell.BoardInterface
import modell.gameComponents.{Cell, Detective, GameInformation, MisterX, Player, Ticket}
import play.api.libs.json.{JsValue, Json}

import scala.collection.mutable.ListBuffer

object JsonHelper:

	def jsonToDataObject(board: String): GameData =
		val json: JsValue = Json.parse(board)
		
		val info = json \ "gameInformation"
		val travelLog = ListBuffer[Int]()
		val travelLogString = info.get("travelLog").toString()
		("""\d+""".r findAllIn travelLogString).foreach(x => travelLog += x.toInt)
		val loadedGameInfo = GameInformation(
			travelLog,
			info.get("revealCounter").toString().toInt,
			info.get("currentPlayer").toString().toInt
		)
		val gameId = info.get("gameId").toString().toInt
		
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
		
		GameData(loadedPlayers, loadedGameInfo, gameId)

	def gameToJsonString(game: GameData, gameId: Int): Option[String] =
		Option(Json.obj(
			"gameInformation" -> Json.obj(
				"currentPlayer" -> game.gameInfo.currentPlayer,
				"travelLog" -> game.gameInfo.travelLog.mkString(" "),
				"revealCounter" -> game.gameInfo.revealCounter,
				"playerNumber" -> game.player.size,
				"gameId" -> gameId
			),
			"players" -> Json.toJson(
				for player <- game.player
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
					)
			)
		).toString)

case class GameData(var player: Vector[Player] = Vector[Player](), var gameInfo: GameInformation = GameInformation(),
                    var gameId: Int = 1)