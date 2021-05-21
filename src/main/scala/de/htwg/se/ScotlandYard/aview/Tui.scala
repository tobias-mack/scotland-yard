
package de.htwg.se.ScotlandYard.aview
import de.htwg.se.ScotlandYard.controller.Controller
import de.htwg.se.ScotlandYard.util.{Observer, State, UI}


class Tui (controller: Controller) extends UI with Observer{

  controller.add(this)
  override def processInput(input: String): Unit = controller.exec(input)

  override def update(): Boolean = {
    println(controller)
    true
  }
}

