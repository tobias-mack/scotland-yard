import de.htwg.se.ScotlandYard.model.{Cell, Player}
val cell1 = Cell(1,1,List(2),0)
val cell2 = Cell(1,2,List(2),0)
val cell3 = Cell(1,3,List(3),0)
cell1

val newPlayer = Player("hi", (2,3,4),cell1)

val newPlayer1 = newPlayer.moveTo(cell2,1)

newPlayer1.moveTo(cell2,1)
