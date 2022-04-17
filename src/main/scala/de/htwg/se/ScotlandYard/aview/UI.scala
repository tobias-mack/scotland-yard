package de.htwg.se.ScotlandYard.aview

import de.htwg.se.ScotlandYard.controller.controllerBaseImpl.{GameState, State}

trait UI:
	def processInput(input: String): State[GameState]
