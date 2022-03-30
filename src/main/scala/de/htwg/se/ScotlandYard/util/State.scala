package de.htwg.se.ScotlandYard.util

import de.htwg.se.ScotlandYard.controller.controllerBaseImpl.GameState

trait State[T]:
  def handle(string: String, state: GameState): Unit
