package de.htwg.se.ScotlandYard.controller.controllerBaseImpl

case class LoosingState(controller: Controller) {
  def handle(): Unit = {
    println("YOU LOST. NO TICKETS ANYMORE.")
  }
}
