package de.htwg.se.ScotlandYard.model

case class Detective(override val name: String = "detective",
                     override val cell: Cell,
                     override val ticket: Ticket,
                     override val typ: Int = 0
                    ) extends Player(name, cell, ticket, typ) {

  override def setName(player: Player, name1: String): Player = {
    player.asInstanceOf[Detective].copy(name = name1 )
  }
  override def setCell(player: Player, cell1: Cell): Player = {
    player.asInstanceOf[Detective].copy(cell = cell1)
  }
  override def setTicket(player: Player, ticket1: Ticket): Player = {
    player.asInstanceOf[Detective].copy(ticket = ticket1)
  }
}
