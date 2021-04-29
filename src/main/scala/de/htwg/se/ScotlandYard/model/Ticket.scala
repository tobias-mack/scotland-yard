package de.htwg.se.ScotlandYard.model

case class Ticket(taxi: Int = 0,
                  bus: Int = 0,
                  subway: Int = 0,
                  black: Int = 0) {
/*  override def toString: String = {
    val ret = "Taxi: " + taxi.toString + " Bus: " + bus.toString + " Sub: " + subway.toString + " Black: " + black.toString + "||||"
    ret
  }*/

}
