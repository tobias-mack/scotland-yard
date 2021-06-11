package de.htwg.se.ScotlandYard.model.gameComponents

trait BoardStrategyTemplate {
  private var player = None:Option[Player]
  private var newData = None:Option[Player]

  def movePlayer(board: Board, pos: Int, playerNumber: Int, transport: Int): Board = {
    player = Some(board.player(playerNumber))
    transport match {
      case 1 => newData = Some(taxi(pos))
      case 2 => newData = Some(bus(pos))
      case 3 => newData = Some(subway(pos))
      case 4 => newData = Some(black(pos))
    }
    updatePlayer(board,playerNumber, newData)
  }
  def taxi(pos: Int): Player ={
    newDataTaxi(player,pos)
  }
  def bus(pos: Int): Player ={
    newDataBus(player,pos)
  }
  def subway(pos: Int): Player ={
    newDataSubway(player,pos)
  }
  def black(pos: Int): Player ={
    newDataBlack(player,pos)
  }
  def newDataTaxi(player:Option[Player],pos:Int):Player ={
    player.get.setCell(player.get, Cell(pos),Ticket(player.get.ticket.taxi - ticketUsage,player.get.ticket.bus,
      player.get.ticket.subway, player.get.ticket.black))
  }
  def newDataBus(player:Option[Player],pos:Int):Player={
    player.get.setCell(player.get, Cell(pos),Ticket(player.get.ticket.taxi, player.get.ticket.bus - ticketUsage,
      player.get.ticket.subway,player.get.ticket.black))
  }
  def newDataSubway(player:Option[Player],pos:Int):Player={
    player.get.setCell(player.get, Cell(pos),Ticket(player.get.ticket.taxi,player.get.ticket.bus,
      player.get.ticket.subway - ticketUsage, player.get.ticket.black))
  }
  def newDataBlack(player:Option[Player],pos:Int):Player={
    player.get.setCell(player.get, Cell(pos),Ticket(player.get.ticket.taxi, player.get.ticket.bus,
      player.get.ticket.subway, player.get.ticket.black - ticketUsage))
  }
  def ticketUsage():Int

  def updatePlayer(board: Board, playerNumber: Int, newData:Option[Player]):Board = {
    val newPlayer = board.player.updated(playerNumber, newData.get) :Vector[Player]
    val newBoard = board.copy(player = newPlayer)
    newBoard
  }
}
object BoardStrategyTemplate {
  def apply(strategy: String): BoardStrategyTemplate = strategy match {
    case "default" => new BoardDefaultStrategy()
    case "hard" => new BoardHardStrategy()
  }
}
