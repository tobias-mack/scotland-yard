package fileIOComponent.databaseComponent

import modell.BoardInterface
import modell.gameComponents.{Board, Player}

import scala.concurrent.Future

trait DBInterface {

  def createDB(): Unit

  def createGame(board: BoardInterface): Int //returns gameId

  def read(gameId: Int): Option[BoardInterface]

  def update(board: BoardInterface): String

  def delete(gameId: Int): Future[Any]
}
