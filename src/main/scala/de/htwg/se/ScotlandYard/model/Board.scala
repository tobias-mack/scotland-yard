package de.htwg.se.ScotlandYard.model

case class Board() {
  def getCell1: Cell = cell1
  def getCell2: Cell = cell2
  def getCell3: Cell = cell3

  val startingTickets: (Int, Int, Int) = (11,8,4)
  val cell1: Cell = Cell(1,1,List(0),0)
  val cell2: Cell = Cell(1,2,List(0),0)
  val cell3: Cell = Cell(1,3,List(0),0)

  val player1: Player = Player("player1",startingTickets,cell1)
  val mrX : Player = Player("MrX",startingTickets,cell3)

}
