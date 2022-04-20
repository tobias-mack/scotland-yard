package de.htwg.se.ScotlandYard.controller.controllerBaseImpl

import scala.util.{Failure, Success, Try}

case class MoveToState(controller: Controller) extends State[GameState]:
  override def handle(input: String, state: GameState): Unit =
    val position: Try[Int] = controller.posToInt(input)
    position match
      case Success(value) =>
        if checkPossDest(value, controller.chosenTransport) then
          state.nextState(TransportState(controller))
          movePlayer(value, controller.chosenTransport)
          if controller.checkReveal() then
            println("REVEAL of MisterX's Location")
            controller.travelLog.clear()
          controller.nextPlayer()
        else
          println(s"Not possible to move to $input. Try again.")
      case Failure(s) =>
        println(s"Not a number. Please try again. - $s")
        state.nextState(MoveToState(controller))

  def movePlayer(position: Int, transport: Int): Unit =
    controller.movePlayer(position, transport)

  def checkPossDest(position: Int, transport: Int): Boolean =
    controller.checkPossDest(position, transport)