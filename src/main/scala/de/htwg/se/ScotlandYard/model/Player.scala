package de.htwg.se.ScotlandYard.model


/*
* status also represents the order in which the players make a move
*
*/
case class Player(name: String, tickets: (Int,Int,Int), position: Int) {

   def showTicketsLeft(): (Int,Int,Int) = tickets
   def getPosition: Int = position
   def getName: String = name

   def moveTo(field: Int): Player = {
      val newTickets = (showTicketsLeft()._1 -1, showTicketsLeft()._2, showTicketsLeft()._3)
      val ret = Player(getName,newTickets,field)
      ret
   }

}

