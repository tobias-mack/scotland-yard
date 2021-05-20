package de.htwg.se.ScotlandYard.controller

import de.htwg.se.ScotlandYard.util.State

case class MoveToState(controller: Controller) extends State[GameState] {
  override def handle(input: String, state: GameState): Unit = {
    println(s"Player ${controller.playerAdded+1}, type your name: ")
    controller.addDetective(input)
    movePlayer(input,controller.chosenTransport)
    state.nextState(NextPlayerState(controller))
  }
  def movePlayer(position: String, transport: Int): Unit = {
    controller.movePlayer(position.toInt,transport)
  }

}

