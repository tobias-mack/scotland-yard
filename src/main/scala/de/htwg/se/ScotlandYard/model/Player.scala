package de.htwg.se.ScotlandYard.model

case class Player(name: String, status: Int) {
   override def toString:String = name
   def mrX : Boolean = status == 0
   def hunter: Boolean = status != 0


}

