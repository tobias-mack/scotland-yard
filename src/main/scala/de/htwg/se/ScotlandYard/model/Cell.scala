package de.htwg.se.ScotlandYard.model

case class Cell(value:Int, name:Int, connection: List[Int], occupancy: Int) {
  def isTaxi : Boolean = value == 1
  def isBus : Boolean = value == 2
  def isSub : Boolean = value == 3

  def getName : Int = name
  def getConnections : List[Int] = connection
  def occupie: Cell = {
    copy(value, name, connection, 1)
  }
  def vacant: Cell = {
    copy(value,name,connection,0)
  }
}
