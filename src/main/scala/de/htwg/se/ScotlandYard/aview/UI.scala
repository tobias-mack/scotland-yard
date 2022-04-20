package de.htwg.se.ScotlandYard.aview

import controller.controllerBaseImpl.{GameState, State}

trait UI:
	def processInput(input: String): State[GameState]
