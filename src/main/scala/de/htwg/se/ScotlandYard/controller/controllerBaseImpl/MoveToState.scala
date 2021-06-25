package de.htwg.se.ScotlandYard.controller.controllerBaseImpl

import de.htwg.se.ScotlandYard.util.State

import scala.util.{Failure, Success, Try}

case class MoveToState(controller: Controller) extends State[GameState] {
  override def handle(input: String, state: GameState): Unit = {
    val position: Try[Int] = controller.posToInt(input)
    position match {
      case Success(value) =>
        if (checkPossDest(value, controller.chosenTransport)) {
          movePlayer(value, controller.chosenTransport)
          state.nextState(NextPlayerState(controller))
        } else {
          println(s"not possible to move to $input. Try again.")
          state.nextState(MoveToState(controller))
        }
      case Failure(_) =>
        println(s"not a number. Try again.")
        state.nextState(MoveToState(controller))
    }
  }

  def movePlayer(position: Int, transport: Int): Unit = {
    controller.movePlayer(position, transport)
  }

  def checkPossDest(position: Int, transport: Int): Boolean = {
    controller.checkPossDest(position, transport)
  }
}
