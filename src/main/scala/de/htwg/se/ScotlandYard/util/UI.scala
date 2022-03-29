package de.htwg.se.ScotlandYard.util

import de.htwg.se.ScotlandYard.controller.controllerBaseImpl.GameState

trait UI:
    def processInput(input: String): State[GameState]
