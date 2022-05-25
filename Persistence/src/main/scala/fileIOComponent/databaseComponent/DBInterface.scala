package fileIOComponent.databaseComponent

import modell.BoardInterface
import modell.gameComponents.{Board, Player}

import scala.concurrent.Future

trait DBInterface {

  def createDB(): Unit

  def createGame(board: String): Int //returns gameId

  def read(gameId: Int): Option[String] //gameAsJson

  def update(board: String): String //gameAsJson

  def delete(gameId: Int): Unit
}
