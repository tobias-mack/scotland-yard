package de.htwg.se.ScotlandYard.model

case class Cell(typ: Int = 1,
                number: Int = 0,
                nearbyTaxi: List[Int] = List(),
                nearbyBus: List[Int] = List(),
                nearbySub: List[Int] = List()) {

}
