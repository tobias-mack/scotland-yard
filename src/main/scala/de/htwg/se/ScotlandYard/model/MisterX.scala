package de.htwg.se.ScotlandYard.model

case class MisterX(override val name: String,
                   override val cell: Cell = Cell(),
                   override val ticket: Ticket = Ticket(9,5,3,5),
                   override val typ: Int = 1
                  ) extends Player(name,cell,ticket,typ) {

  override def setName(player: Player, name1: String): Player = {
    player.asInstanceOf[MisterX].copy(name = name1)
  }
  override def setCell(player: Player, cell1: Cell,ticket1: Ticket): Player = {
    player.asInstanceOf[MisterX].copy(cell = cell1, ticket = ticket1)
  }
  override def setTicket(player: Player, ticket1: Ticket): Player = {
    player.asInstanceOf[MisterX].copy(ticket = ticket1)
  }
/*  override def toString: String = {
    val ret = "Name: " + name + " position: " + cell.number + " Tickets: " + ticket.toString + "||||"
    ret
  }*/
}
