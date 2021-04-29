
package de.htwg.se.ScotlandYard.aview
import de.htwg.se.ScotlandYard.controller
import de.htwg.se.ScotlandYard.controller.Controller
import de.htwg.se.ScotlandYard.model.Board
import de.htwg.se.ScotlandYard.util.Observer

import scala.io.StdIn.readLine

class Tui (controller: Controller) extends Observer{
  controller.add(this)

  def processInputLine(input: String): Unit = {
    input match {
      case "moveD" => println("where to?")
        val pos = readLine()
        val pos1 = pos.toInt
        controller.moveDetective(pos1)
    }
  }

  override def update(): Unit = println(controller)
}

