package de.htwg.se.ScotlandYard.model

case class Board(cell1: Cell,cell2: Cell,cell3: Cell) {
  def getCell1: Cell = cell1
  def getCell2: Cell = cell2
  def getCell3: Cell = cell3
}
