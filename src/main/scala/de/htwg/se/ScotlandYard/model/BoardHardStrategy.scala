package de.htwg.se.ScotlandYard.model

class BoardHardStrategy extends BoardStrategyTemplate {
    override def movePlayer(board: Board, pos: Int, playerNumber: Int, transport: Int): Board = {
      transport match {
        case 1 => strategy1(board, pos, playerNumber)
        case 2 => strategy2(board, pos, playerNumber)
        case 3 => strategy3(board, pos, playerNumber)
        case 4 => strategy4(board, pos, playerNumber)
      }
    }
    def strategy1(board: Board, pos: Int, playerNumber: Int): Board = {
      val Player = board.player(playerNumber)
      val newData = Player.setCell(Player, Cell(pos),Ticket(Player.ticket.taxi - 2,Player.ticket.bus,Player.ticket.subway,Player.ticket.black))
      val newPlayer = board.player.updated(playerNumber, newData)
      val newBoard = board.copy(player = newPlayer)
      newBoard
    }

    def strategy2(board: Board, pos: Int, playerNumber: Int): Board = {
      val Player = board.player(playerNumber)
      val newData = Player.setCell(Player, Cell(pos), Ticket(Player.ticket.taxi, Player.ticket.bus - 2, Player.ticket.subway, Player.ticket.black))
      val newPlayer = board.player.updated(playerNumber, newData)
      val newBoard = board.copy(player = newPlayer)
      newBoard
    }

    def strategy3(board: Board, pos: Int, playerNumber: Int): Board = {
      val Player = board.player(playerNumber)
      val newData = Player.setCell(Player, Cell(pos),Ticket(Player.ticket.taxi,Player.ticket.bus,Player.ticket.subway - 2,Player.ticket.black))
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

}
