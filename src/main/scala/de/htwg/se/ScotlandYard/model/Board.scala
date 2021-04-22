package de.htwg.se.ScotlandYard.model

case class Board() {

  val cell1: Cell = Cell(1,1,List(2),0)
  val cell2: Cell = Cell(2,2,List(3),0)
  val cell3: Cell = Cell(3,3,List(1),0)

  def getCells: (Int,Int,Int) = {
    (cell1.getName, cell2.getName, cell3.getName)
  }

}
