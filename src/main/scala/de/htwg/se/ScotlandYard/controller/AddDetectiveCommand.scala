package de.htwg.se.ScotlandYard.controller
import de.htwg.se.ScotlandYard.util.Command


class AddDetectiveCommand(name: String, controller: Controller) extends Command {
  override def doStep(): Unit ={
    controller.board = controller.board.addDetective(controller.board, name)
    controller.playerAdded+=1
    if(controller.playerNumber==controller.playerAdded) controller.gameState.nextState(NextPlayerState(controller))
  }
  override def undoStep(): Unit= {
    val newBoard = controller.board.copy(player = controller.board.player.dropRight(1))
    controller.board = newBoard
  }
  override def redoStep(): Unit={
    doStep()
  }

}