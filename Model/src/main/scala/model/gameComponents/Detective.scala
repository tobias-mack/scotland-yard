package model.gameComponents

case class Detective(override val name: String,
                     override val cell: Cell = Cell(1),
                     override val ticket: Ticket = Ticket(10, 8, 4),
                     override val typ: Int = 0
                    ) extends Player(name, cell, ticket, typ) :

  override def setName(player: Player, name1: String): Player =
    player.asInstanceOf[Detective].copy(name = name1)

  override def setCell(player: Player, cell1: Cell, ticket1: Ticket): Player =
    player.asInstanceOf[Detective].copy(cell = cell1, ticket = ticket1)
