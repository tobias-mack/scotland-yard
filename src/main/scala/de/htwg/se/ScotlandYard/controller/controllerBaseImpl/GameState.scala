package de.htwg.se.ScotlandYard.controller.controllerBaseImpl

import de.htwg.se.ScotlandYard.util.State

case class GameState(controller: Controller):
  var state: State[GameState] = StartState(controller)

  def handle(string: String): State[GameState] =
    state.handle(string, this)
    this.state

  def nextState(state: State[GameState]): Unit =
    this.state = state