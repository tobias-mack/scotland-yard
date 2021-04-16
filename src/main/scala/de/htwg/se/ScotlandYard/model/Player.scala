package de.htwg.se.ScotlandYard.model
/*
* status also represents the order in which the players make a move
*
*/
case class Player(name: String, status: Int) {
   override def toString:String = name
   def isMrX : Boolean = status == 0
   def isHunter: Boolean = status != 0
}

