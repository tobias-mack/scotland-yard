package de.htwg.se.ScotlandYard.controller

import de.htwg.se.ScotlandYard.util.State

case class MoveToState(controller: Controller) extends State[GameState] {
  override def handle(input: String, state: GameState): Unit = {
    val position = posToInt(input)
    if(checkPossDest(position,controller.chosenTransport)){
      movePlayer(position,controller.chosenTransport)
    }else(state.nextState(UnknownCommandState(controller))) // TODO undo controller.nextPlayer damit selber spieler nochmal ziehen darf
    state.nextState(NextPlayerState(controller))
  }
  def movePlayer(position: Int, transport: Int): Unit = {
    controller.movePlayer(position,transport)
  }
  def checkPossDest(position: Int,transport: Int):Boolean = {
    controller.checkPossDest(position,transport)
  }
  def posToInt(position: String): Int = position.toInt
}

