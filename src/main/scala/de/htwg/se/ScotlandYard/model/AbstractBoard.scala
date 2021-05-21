package de.htwg.se.ScotlandYard.model

abstract class AbstractBoard(cell : Vector[Cell],
                             player: Vector[Player]) {


  def movePlayer(board: Board, pos: Int, playerNumber: Int,transport: Int): Board = {
    transport match {
      case 1 => strategy1(board, pos, playerNumber)
      case 2 => strategy2(board, pos, playerNumber)
      case 3 => strategy3(board, pos, playerNumber)
      case 4 => strategy4(board, pos, playerNumber)
    }
  }
  def strategy1(board: Board, pos: Int, playerNumber: Int): Board = {
    val Player = board.player(playerNumber)
                               val newData = Player.setCell(Player, Cell(pos),Ticket(Player.ticket.taxi - 1,Player.ticket.bus,Player.ticket.subway,Player.ticket.black))
                               val newPlayer = board.player.updated(playerNumber, newData)
    val newBoard = board.copy(player = newPlayer)
    newBoard
  }

  def strategy2(board: Board, pos: Int, playerNumber: Int): Board = {
    val Player = board.player(playerNumber)
    val newData = Player.setCell(Player, Cell(pos), Ticket(Player.ticket.taxi, Player.ticket.bus - 1, Player.ticket.subway, Player.ticket.black))
    val newPlayer = board.player.updated(playerNumber, newData)
    val newBoard = board.copy(player = newPlayer)
    newBoard
  }

  def strategy3(board: Board, pos: Int, playerNumber: Int): Board = {
    val Player = board.player(playerNumber)
    val newData = Player.setCell(Player, Cell(pos),Ticket(Player.ticket.taxi,Player.ticket.bus,Player.ticket.subway - 1,Player.ticket.black))
    val newPlayer = board.player.updated(playerNumber, newData)
    val newBoard = board.copy(player = newPlayer)
    newBoard
  }

  def strategy4(board: Board, pos: Int, playerNumber: Int): Board = {
    val Player = board.player(playerNumber)
    val newData = Player.setCell(Player, Cell(pos),Ticket(Player.ticket.taxi,Player.ticket.bus,Player.ticket.subway,Player.ticket.black - 1))
    val newPlayer = board.player.updated(playerNumber, newData)
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
    val Statement = new StringBuilder()
    for (n <- player){
      Statement.append("  \u001b[30m" + n.name + " \u001b[0mis at \u001b[34mposition " + n.cell.number + " \u001b[0mand has\u001b[33m " +
        n.ticket.taxi + " Taxi \u001b[0mtickets,\u001b[32m" + n.ticket.bus + " Bus \u001b[0mtickets, \u001b[31m" +
        n.ticket.subway + " Subway \u001b[0mtickets" + "; \n")
    }
    Statement.toString()
  }
}

