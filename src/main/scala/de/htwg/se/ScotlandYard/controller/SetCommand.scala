package de.htwg.se.ScotlandYard.controller
import de.htwg.se.ScotlandYard.util.Command

class SetCommand(row:Int, col: Int, value: String, controller: Controller) extends Command {
  override def doStep: Unit ={}//=   controller.board = controller.board.

  override def undoStep: Unit= {}

  override def redoStep: Unit={}

}