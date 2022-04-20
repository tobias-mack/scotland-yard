package model.gameComponents

import model.BoardInterface

trait BoardStrategyTemplate:

  def movePlayer(board: BoardInterface, pos: Int, playerNumber: Int, transport: Int): Board =
    val player = board.player(playerNumber)
    updatePlayer(board, playerNumber, Some(newData(player, pos, transport)))

  def newData(player: Player, pos: Int, transport: Int): Player =
    val t = player.ticket
    transport match
      case 1 => player.setCell(player, Cell(pos), Ticket(t.taxi - ticketUsage(), t.bus, t.subway, t.black))
      case 2 => player.setCell(player, Cell(pos), Ticket(t.taxi, t.bus - ticketUsage(), t.subway, t.black))
      case 3 => player.setCell(player, Cell(pos), Ticket(t.taxi, t.bus, t.subway - ticketUsage(), t.black))
      case 4 => player.setCell(player, Cell(pos), Ticket(t.taxi, t.bus, t.subway, t.black - ticketUsage()))

  def ticketUsage(): Int

  def updatePlayer(board: BoardInterface, playerNumber: Int, newData: Option[Player]): Board =
    val newPlayer = board.player.updated(playerNumber, newData.get): Vector[Player]
    val newBoard = Board(newPlayer)
    newBoard

object BoardStrategyTemplate:
  def apply(strategy: String): BoardStrategyTemplate = strategy match
    case "default" => new BoardDefaultStrategy()
    case "hard" => new BoardHardStrategy()
