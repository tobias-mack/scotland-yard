package modell.gameComponents

case class MisterX(override val name: String,
                   override val cell: Cell = Cell(5),
                   override val ticket: Ticket = Ticket(9, 5, 3, 5),
                   override val typ: Int = 1
                  ) extends Player(name, cell, ticket, typ) :

  override def setName(player: Player, name1: String): Player =
    player.asInstanceOf[MisterX].copy(name = name1)

  override def setCell(player: Player, cell1: Cell, ticket1: Ticket): Player =
    player.asInstanceOf[MisterX].copy(cell = cell1, ticket = ticket1)
