package de.htwg.se.ScotlandYard.model

case class Board( cell : Vector[Cell] = Vector[Cell](Cell(number = 1), Cell(number = 2), Cell(number = 3)),
                  player: Vector[Player] = Vector[Player](MisterX("mrx"), Detective("det"))) {

  def moveDetective(board: Board, pos: Int): Board = {
    val player = board.player(1)
    val newPosition = player.setCell(player,Cell(pos))
    val newBoard = board.copy(player = Vector[Player](board.player(0), newPosition))
    newBoard
  }
  def moveMRX(board: Board, pos: Int): Board = {
    val player = board.player(0)
    val newPosition = player.setCell(player, Cell(pos))
    val newBoard = board.copy(player = Vector[Player](board.player(0), newPosition))
    newBoard
  }

}
