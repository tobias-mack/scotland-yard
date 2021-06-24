package de.htwg.se.ScotlandYard.model.gameComponents


case class Cell (number: Int = 0,
                nearbyTaxi: List[Int] = List(),
                nearbyBus: List[Int] = List(),
                nearbySub: List[Int] = List()) {

  @Override
  override def toString: String = {
    val s = "" + number.toString + " " + nearbyTaxi.toString() + " " + nearbyBus.toString() + " " + nearbySub.toString()
    s
  }

}