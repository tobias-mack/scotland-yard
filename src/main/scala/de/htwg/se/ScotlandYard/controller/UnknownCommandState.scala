package de.htwg.se.ScotlandYard.controller

import de.htwg.se.ScotlandYard.util.State

case class UnknownCommandState(controller: Controller) extends State[GameState] {
  override def handle(input: String, state: GameState): Unit = {
    println("unknown command")
    state.nextState(NextPlayerState(controller))
    //TODO: undo command so that playerOrder stays correct and is not incremented by wrong input
  }
}
