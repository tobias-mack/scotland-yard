package de.htwg.se.ScotlandYard.model

case class MisterX(override val name: String,
                   override val cell: Cell,
                   override val ticket: Ticket,
                   override val typ: Int = 1
                  ) extends Player(name,cell,ticket,typ) {

  override def setName(player: Player, name1: String): Player = {
    player.asInstanceOf[MisterX].copy(name = name1)
  }
  override def setCell(player: Player, cell1: Cell): Player = {
    player.asInstanceOf[MisterX].copy(cell = cell1)
  }
  override def setTicket(player: Player, ticket1: Ticket): Player = {
    player.asInstanceOf[MisterX].copy(ticket = ticket1)
  }
}
