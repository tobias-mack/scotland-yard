package de.htwg.se.ScotlandYard.model



/*
* status also represents the order in which the players make a move
*
*/
case class Player(name: String, tickets: (Int,Int,Int), position: Cell) {

   def ticketLeft(): (Int,Int,Int) = tickets
   def getPosition: Cell = position
   def getName: String = name

   def moveTo(field: Cell,ticket: Int): Player = {
      if(field.occupancy == 1) {
         println("Cell already occupied")
         copy(getName,ticketLeft(),position)
      }
      field.occupie
      position.vacant
      ticket match {
         case 1 => copy(name,(ticketLeft()._1 -1,ticketLeft()._2, ticketLeft()._3),field)
         case 2 => copy(name,(ticketLeft()._1,ticketLeft()._2 -1, ticketLeft()._3),field)
         case 3 => copy(name,(ticketLeft()._1,ticketLeft()._2, ticketLeft()._3 -1),field)
      }
   }

}

