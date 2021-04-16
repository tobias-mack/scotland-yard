package de.htwg.se.ScotlandYard.model

case class Board() {

  val cell1: Cell = Cell(1,1,List(2))
  val cell2: Cell = Cell(2,2,List(3))
  val cell3: Cell = Cell(3,3,List(1))

  def getCells: (Int,Int,Int) = {
    (cell1.getName, cell2.getName, cell3.getName)
  }
}
