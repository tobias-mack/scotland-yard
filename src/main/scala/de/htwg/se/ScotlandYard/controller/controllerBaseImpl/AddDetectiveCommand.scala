package de.htwg.se.ScotlandYard.controller.controllerBaseImpl

import de.htwg.se.ScotlandYard.model.gameComponents.{Board, Cell, Ticket}
import de.htwg.se.ScotlandYard.util.Command


class AddDetectiveCommand(name: String, controller: Controller) extends Command {
  override def doStep(): Unit ={
    if(controller.playerAdded == 0) {
      controller.board = controller.board.addDetective(controller.board, name, Cell(5), Ticket(10,8,5,3))
      controller.playerAdded+=1
    } else {
      controller.board = controller.board.addDetective(controller.board, name, Cell(1), Ticket(9,8,4))
      controller.playerAdded+=1
    }

    if(controller.playerNumber==controller.playerAdded) controller.gameState.nextState(NextPlayerState(controller))
  }
  override def undoStep(): Unit= {
    val newBoard = Board(player1 = controller.board.player.dropRight(1))
    controller.playerAdded-=1
    controller.board = newBoard
    controller.gameState.nextState(PlayerNamesState(controller))
  }
  override def redoStep(): Unit={
    doStep()
  }

}