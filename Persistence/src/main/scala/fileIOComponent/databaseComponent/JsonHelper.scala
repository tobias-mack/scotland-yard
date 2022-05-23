package fileIOComponent.databaseComponent

import modell.BoardInterface
import modell.gameComponents.{GameInformation, Player}
import play.api.libs.json.{JsValue, Json}

import scala.collection.mutable.ListBuffer

object JsonHelper:

  def jsonToDataObject(board: String): GameData =
    val gameInfo: GameInformation = GameInformation()
    val player: Vector[Player] = Vector[Player]()
    GameData(player, gameInfo)

case class GameData(
                     val player: Vector[Player],
                     val gameInfo: GameInformation
                   )