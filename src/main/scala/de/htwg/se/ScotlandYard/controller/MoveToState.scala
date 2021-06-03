package de.htwg.se.ScotlandYard.controller

import de.htwg.se.ScotlandYard.util.State

import scala.util.{Failure, Success, Try}

case class MoveToState(controller: Controller) extends State[GameState] {
  override def handle(input: String, state: GameState): Unit = {
    val position : Try[Int] = controller.posToInt(input)
    position match{
      case Success(value) =>
        if(checkPossDest(value,controller.chosenTransport)){
          movePlayer(value,controller.chosenTransport)
        }
      case Failure(_) => state.nextState(UnknownCommandState(controller))
    }
    // TODO undo controller.nextPlayer damit selber spieler nochmal ziehen darf*/
    state.nextState(NextPlayerState(controller))
  }
  def movePlayer(position: Int, transport: Int): Unit = {
    controller.movePlayer(position,transport)
  }
  def checkPossDest(position: Int,transport: Int):Boolean = {
    controller.checkPossDest(position,transport)
  }
}

