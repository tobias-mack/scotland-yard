package de.htwg.se.ScotlandYard.controller.controllerBaseImpl

case class LoosingState(controller: Controller) extends State[GameState] :
  override def handle(string: String, state: GameState): Unit =
    println("Detectives loose. NO TICKETS ANYMORE.")