package de.htwg.se.ScotlandYard.model

case class Cell(typ: Int, number: Int, nearbyTaxi: List[Int], nearbyBus: List[Int], nearbySub: List[Int]) {

}
