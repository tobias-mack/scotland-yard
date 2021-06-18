package de.htwg.se.ScotlandYard.controller.controllerBaseImpl

case class WinningState(controller: Controller) {
  def handle(): Unit = {
    println("WINNER WINNER CHICKEN DINNER")
  }
}
