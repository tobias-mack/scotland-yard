package de.htwg.se.ScotlandYard.controller

import de.htwg.se.ScotlandYard.util.State

import scala.io.StdIn.readLine

case class StartState(controller: Controller) extends State[GameState] {
  override def handle(input: String, state: GameState): Unit = {
    controller.playerNumber = input.toInt
    state.nextState(PlayerNamesState(controller))
  }
}