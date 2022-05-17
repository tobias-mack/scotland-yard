package fileIOComponent.databaseComponent

import model.gameComponents.Player
import scala.concurrent.Future

trait DBInterface {

  def createDB(): Unit

  def readPlayer(playerId: Int): Option[(Int, String, Int, Int, Int, Int, Int, Int)]

  def readAllPlayer(): List[(Int, String, Int, Int, Int, Int, Int, Int)]

  def updatePlayer(playerId: Int, position: Int): String

  def deletePlayer(playerId: Int): Future[Any]

  def createPlayer(playerId: Int, player: Player): Int

}
