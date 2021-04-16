package de.htwg.se.ScotlandYard.model
/*
* status also represents the order in which the players make a move
*
*/
case class Player(name: String, status: Int) {
   override def toString:String = name


   /**
    * returns boolean if player is Mr.X
    * @return Boolean
    */
   def isMrX : Boolean = status == 0

   /**
    * returns boolean if player is Hunter
    * @return Boolean
    */
   def isHunter: Boolean = status != 0

}

