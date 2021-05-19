
package de.htwg.se.ScotlandYard.aview
import de.htwg.se.ScotlandYard.controller.{Controller}
import de.htwg.se.ScotlandYard.util.{Observer, State, UI}

import scala.io.StdIn.readLine

class Tui (controller: Controller) extends UI with Observer{

  controller.add(this)
  override def processInput(input: String): Unit = controller.exec(input)

  def movePlayer(position: Int, transport: Int): Unit = {
    controller.movePlayer(position,transport)
  }
  override def update(): Boolean = {
    println(controller)
    true
  }
}

