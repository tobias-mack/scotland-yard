package de.htwg.se.ScotlandYard.model

case class Cell(number: Int = 0,
                nearbyTaxi: List[Int] = List(),
                nearbyBus: List[Int] = List(),
                nearbySub: List[Int] = List()) {

/*  override def toString: String = {
    val ret = "Number: " + number.toString + " Type: Taxi" + "||||"
    ret
  }*/
}
