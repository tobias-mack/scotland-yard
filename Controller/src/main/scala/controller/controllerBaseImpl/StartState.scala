package controller.controllerBaseImpl

import scala.util.{Failure, Success, Try}

case class StartState(controller: Controller) extends State[GameState] :
  override def handle(input: String, state: GameState): Unit =
    val playerNumber: Try[Int] = controller.posToInt(input)
    playerNumber match
      case Success(value) =>
        if !controller.numOfPlayers.contains(value) then
          println(s"$value is not in number of players ${controller.numOfPlayers}. Please try again.")
          state.nextState(StartState(controller))
        else
          controller.playerNumber = value
          state.nextState(PlayerNamesState(controller))
      case Failure(s) => println(s"Not a number. Please try again. - $s")
        state.nextState(StartState(controller))