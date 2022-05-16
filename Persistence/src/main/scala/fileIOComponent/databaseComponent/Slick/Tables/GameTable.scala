package fileIOComponent.databaseComponent.Slick.Tables

import slick.jdbc.PostgresProfile.api.*
import model.gameComponents.{GameInformation, Player}


class GameTable(tag: Tag) extends Table[(Int, String, Int, Int)](tag, "GAME") {

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def travelLog = column[String]("travelLog")

  def revealCounter = column[Int]("revealCounter")

  def currentPlayer = column[Int]("currentPlayer")

  override def * = (id, travelLog, currentPlayer, revealCounter)

}
