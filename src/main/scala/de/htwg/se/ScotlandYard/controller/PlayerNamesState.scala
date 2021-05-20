package de.htwg.se.ScotlandYard.controller

import de.htwg.se.ScotlandYard.util.State

case class PlayerNamesState(controller: Controller) extends State[GameState] {
  override def handle(input: String, state: GameState): Unit = {
    println(s"Player ${controller.playerAdded+1}, accepted. ")
    controller.addDetective(input)
  }
}