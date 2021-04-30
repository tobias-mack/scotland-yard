package de.htwg.se.ScotlandYard.model

case class Board( cell : Vector[Cell] = Vector[Cell](Cell(1),Cell(2),Cell(3)),
                  player: Vector[Player] = Vector[Player]()) {


  def movePlayer(board: Board, pos: Int, playerNumber: Int): Board = {
    val Player = board.player(playerNumber)
    val newPosition = Player.setCell(Player, Cell(pos))
    val newPlayer = board.player.updated(playerNumber,newPosition)
    val newBoard = board.copy(player = newPlayer)
    newBoard
  }
  def addDetective(board: Board, newName: String): Board = {
    val MrX = MisterX(name = newName)
    val newPlayer = Detective(name = newName)
    val newBoard = board.copy(player = if(board.player.isEmpty){board.player :+ MrX} else{board.player :+ newPlayer})
    newBoard
  }

  override def toString: String = {
    val board = new StringBuilder("  Board: ")
    for (n <- cell){
      board.append(n.number + " , ")
    }
    board.setLength(board.length() - 2)
    val Statement = new StringBuilder()
    for (n <- player){
      Statement.append("  " + n.name + " is at position " + n.cell.number + ", \n")
    }
    Statement.append(board)
    Statement.toString()
  }
}
