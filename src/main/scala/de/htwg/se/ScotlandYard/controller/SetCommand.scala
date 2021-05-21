package de.htwg.se.ScotlandYard.controller
import de.htwg.se.ScotlandYard.model.Cell
import de.htwg.se.ScotlandYard.util.Command

class SetCommand(currpos: Int,newpos: Int, transport: Int, controller: Controller) extends Command {
  override def doStep(): Unit ={
    controller.movePlayer(newpos, transport)
  }
  override def undoStep(): Unit= {
    controller.movePlayer(currpos,4)
  }
  override def redoStep(): Unit={
    controller.movePlayer(newpos, transport)
  }

}