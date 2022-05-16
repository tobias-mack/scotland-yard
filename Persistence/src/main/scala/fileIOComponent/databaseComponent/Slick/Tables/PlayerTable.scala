package fileIOComponent.databaseComponent.Slick.Tables

import slick.jdbc.PostgresProfile.api.*

class PlayerTable(tag: Tag) extends Table[(Int, String, Int, Int, Int, Int, Int, Int)](tag, "PLAYER") {

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def name = column[String]("name")

  def cell = column[Int]("cell")

  def taxi = column[Int]("taxi")

  def bus = column[Int]("bus")

  def sub = column[Int]("sub")

  def black = column[Int]("black")

  def typ = column[Int]("typ")

  override def * = (id, name, cell, taxi, bus, sub, black, typ)

}