package de.htwg.se.ScotlandYard.controller.controllerBaseImpl

import de.htwg.se.ScotlandYard.util.State

import scala.util.{Failure, Success, Try}

case class StartState(controller: Controller) extends State[GameState] {
  override def handle(input: String, state: GameState): Unit = {
    val playerNumber: Try[Int] = controller.posToInt(input)
    playerNumber match {
      case Success(value) => controller.playerNumber = value
        state.nextState(PlayerNamesState(controller))
      case Failure(_) => println("wrong input")
        state.nextState(StartState(controller))
    }
  }
}
