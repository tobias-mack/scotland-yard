package de.htwg.se.ScotlandYard.controller

import de.htwg.se.ScotlandYard.util.State

import scala.io.StdIn.readLine

case class StartState(controller: Controller) extends State[GameState] {
  override def handle(input: String, state: GameState): Unit = {
    val playerNumber = input.toInt
    val detectives: Unit = Vector.tabulate(playerNumber) {
      n =>
        controller.addDetective(
          if (n == 0) {
            readLine(s"Mister X, type your name: ")
          }
          else {
            readLine(s"Player ${n + 1}, type your name: ")
          })
    }
    state.nextState(NextPlayerState(controller))
  }
}