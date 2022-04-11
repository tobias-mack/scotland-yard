package de.htwg.se.ScotlandYard.controller.controllerBaseImpl

import de.htwg.se.ScotlandYard.util.State

import scala.util.{Failure, Success, Try}

case class StartState(controller: Controller) extends State[GameState] :
  override def handle(input: String, state: GameState): Unit =
    val playerNumber: Try[Int] = controller.posToInt(input)
    playerNumber match
      case Success(value) =>
        if value > controller.maxPlayers then
          println(s"$value has exceeded the maximum number of players. Please try again.")
          state.nextState(StartState(controller))
        else
          controller.playerNumber = value
          state.nextState(PlayerNamesState(controller))
      case Failure(s) => println(s"Not a number. Please try again. - $s")
        state.nextState(StartState(controller))