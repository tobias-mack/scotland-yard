package de.htwg.se.ScotlandYard.model.gameComponents

import de.htwg.se.ScotlandYard.model.BoardInterface

trait BoardStrategyTemplate:

  def movePlayer(board: Option[BoardInterface], pos: Int, playerNumber: Int, transport: Int): Option[Board] =
    val player1 = Some(board.player(playerNumber))
    transport match
      case 1 => updatePlayer(Option(board), playerNumber, Some(newDataTaxi(player1, pos)))
      case 2 => updatePlayer(Option(board), playerNumber, Some(newDataBus(player1, pos)))
      case 3 => updatePlayer(Option(board), playerNumber, Some(newDataSubway(player1, pos)))
      case 4 => updatePlayer(Option(board), playerNumber, Some(newDataBlack(player1, pos)))


  def newDataTaxi(player: Option[Player], pos: Int): Player =
    player.get.setCell(player.get, Cell(pos), Ticket(player.get.ticket.taxi - ticketUsage(), player.get.ticket.bus,
      player.get.ticket.subway, player.get.ticket.black))

  def newDataBus(player: Option[Player], pos: Int): Player =
    player.get.setCell(player.get, Cell(pos), Ticket(player.get.ticket.taxi, player.get.ticket.bus - ticketUsage(),
      player.get.ticket.subway, player.get.ticket.black))

  def newDataSubway(player: Option[Player], pos: Int): Player =
    player.get.setCell(player.get, Cell(pos), Ticket(player.get.ticket.taxi, player.get.ticket.bus,
      player.get.ticket.subway - ticketUsage(), player.get.ticket.black))

  def newDataBlack(player: Option[Player], pos: Int): Player =
    player.get.setCell(player.get, Cell(pos), Ticket(player.get.ticket.taxi, player.get.ticket.bus,
      player.get.ticket.subway, player.get.ticket.black - ticketUsage()))

  def ticketUsage(): Int

  def updatePlayer(board: Option[BoardInterface], playerNumber: Int, newData: Option[Player]): Option[Board] =
    val newPlayer = board.player.updated(playerNumber, newData.get): Vector[Player]
    val newBoard = Board(newPlayer)
    Option(newBoard)

object BoardStrategyTemplate:
  def apply(strategy: String): BoardStrategyTemplate = strategy match
    case "default" => new BoardDefaultStrategy()
    case "hard" => new BoardHardStrategy()
