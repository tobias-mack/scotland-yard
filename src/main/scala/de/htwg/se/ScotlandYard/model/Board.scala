package de.htwg.se.ScotlandYard.model

case class Board( cell : Vector[Cell] = Vector[Cell](),
                  player: Vector[Player] = Vector[Player](MisterX("MisterX"), Detective("d1"), Detective("d2"))) {

  def moveDetective(board: Board, pos: Int): Board = {
    val player1 = board.player(1)
    val newPosition = player1.setCell(player1,Cell(pos))
    val newPlayer = board.player.updated(1,newPosition)
    val newBoard = board.copy(player = newPlayer)
    newBoard
  }
  def moveMRX(board: Board, pos: Int): Board = {
    val player0 = board.player(0)
    val newPosition = player0.setCell(player0, Cell(pos))
    val newPlayer = board.player.updated(0,newPosition)
    val newBoard = board.copy(player = newPlayer)
    newBoard
  }
  def addDetective(board: Board, newname: String, det: Int): Board = {
    val newPlayer = Detective(name = newname)
    val newBoard = board.copy(player = board.player.updated(det,newPlayer))
    newBoard
  }

/*  override def toString: String = {
    val ret = "Cells: " + cell(0).toString() + "Players: " + player(0).toString() + "next:" + player(1).toString + "|||"
    ret
  }*/

}
