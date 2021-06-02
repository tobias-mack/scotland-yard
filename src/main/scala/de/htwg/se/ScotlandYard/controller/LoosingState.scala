package de.htwg.se.ScotlandYard.controller

import scala.sys.exit

case class LoosingState(controller: Controller) {
  def handle(): Unit = {
    println("YOU LOST. NO TICKETS ANYMORE.")
    //exit(0)
  }
  //TODO welcher spieler hat keine Tickets mehr ? bzw. detectives können sich nicht mehr von zelle bewegen?
}
