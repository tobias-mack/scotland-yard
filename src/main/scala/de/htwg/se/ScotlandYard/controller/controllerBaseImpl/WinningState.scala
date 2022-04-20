package de.htwg.se.ScotlandYard.controller.controllerBaseImpl

case class WinningState(controller: Controller) extends State[GameState] :
  override def handle(string: String, state: GameState): Unit =
    println("WINNER WINNER CHICKEN DINNER")