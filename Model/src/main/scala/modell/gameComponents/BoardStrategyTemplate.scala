package modell.gameComponents

import modell.BoardInterface

import scala.collection.mutable.ListBuffer
import scala.language.postfixOps

trait BoardStrategyTemplate:

  def movePlayer(board: BoardInterface, pos: Int, currentPlayer: Int, transport: Int,
    travelLog: ListBuffer[Int], revealCounter: Int): Board =
    val player = board.player(currentPlayer)
    val nextPlayer = (currentPlayer + 1) % board.player.size
    updateBoard(board, currentPlayer, Some(newData(player, pos, transport)), travelLog, revealCounter, nextPlayer)

  def newData(player: Player, pos: Int, transport: Int): Player =
    val t = player.ticket
    transport match
      case 1 => player.setCell(player, Cell(pos), Ticket(t.taxi - ticketUsage(), t.bus, t.subway, t.black))
      case 2 => player.setCell(player, Cell(pos), Ticket(t.taxi, t.bus - ticketUsage(), t.subway, t.black))
      case 3 => player.setCell(player, Cell(pos), Ticket(t.taxi, t.bus, t.subway - ticketUsage(), t.black))
      case 4 => player.setCell(player, Cell(pos), Ticket(t.taxi, t.bus, t.subway, t.black - ticketUsage()))

  def ticketUsage(): Int

  def updateBoard(board: BoardInterface, playerNumber: Int, newData: Option[Player], travelLog: ListBuffer[Int], revealCounter: Int, currentPlayer: Int): Board =
    val newPlayer = board.player.updated(playerNumber, newData.get): Vector[Player]
    val newGameInfo = GameInformation(travelLog, revealCounter, currentPlayer)
    val newBoard = Board(newPlayer, newGameInfo)
    newBoard

object BoardStrategyTemplate:
  def apply(strategy: String): BoardStrategyTemplate = strategy match
    case "default" => new BoardDefaultStrategy()
    case "hard" => new BoardHardStrategy()
