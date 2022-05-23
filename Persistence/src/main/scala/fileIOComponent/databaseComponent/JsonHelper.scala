package fileIOComponent.databaseComponent

import akka.parboiled2.RuleTrace.Optional
import modell.BoardInterface
import modell.gameComponents.{GameInformation, Player}
import play.api.libs.json.{JsValue, Json}

import scala.collection.mutable.ListBuffer

object JsonHelper:

	def jsonToDataObject(board: String): GameData =
		val gameInfo: GameInformation = GameInformation()
		val player: Vector[Player] = Vector[Player]()
		GameData(player, gameInfo)

	def gameToJsonString(game: GameData): Option[String] =
		Option(Json.obj(
			"gameInformation" -> Json.obj(
				"currentPlayer" -> game.gameInfo.currentPlayer,
				"travelLog" -> game.gameInfo.travelLog.mkString(" "),
				"revealCounter" -> game.gameInfo.revealCounter,
				"playerNumber" -> game.player.size
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
                    val gameId: Int = 1)