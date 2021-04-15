package de.htwg.se.ScotlandYard.model

case class Cell(value:Int, name:Int, connection: Int) {
  def isTaxi : Boolean = value == 1
  def isBus : Boolean = value == 2
  def isSub : Boolean = value == 3

  /**
   * returns the name of the cell (INT)
   * @return name
   */
  def getName : Int = name

}
