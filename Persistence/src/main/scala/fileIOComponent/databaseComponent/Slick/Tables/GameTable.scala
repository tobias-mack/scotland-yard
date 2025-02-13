package fileIOComponent.databaseComponent.Slick.Tables

import slick.jdbc.PostgresProfile.api.*
import modell.gameComponents.{GameInformation, Player}


class GameTable(tag: Tag) extends Table[(Int, Int, String, Int, Int)](tag, "GAME") {

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def gameId = column[Int]("gameId")

  def travelLog = column[String]("travelLog")

  def revealCounter = column[Int]("revealCounter")

  def currentPlayer = column[Int]("currentPlayer")

  override def * = (id, gameId, travelLog, currentPlayer, revealCounter)

}
