
package de.htwg.se.ScotlandYard.aview
import de.htwg.se.ScotlandYard.controller.Controller
import de.htwg.se.ScotlandYard.util.Observer

import scala.io.StdIn.readLine

class Tui (controller: Controller) extends Observer{
  controller.add(this)

  def processInputLine(input: String): Unit = {
    input match {
      case "help" =>
        println("moveD = move Detective")
        println("moveX = move MisterX")
        println("q = quit Game")
      case "moveD" => println("where to?")
        val pos = readLine().toInt
        controller.moveDetective(pos)
      case "moveX" => println("where to?")
        val pos1 = readLine().toInt
        controller.moveMRX(pos1)

    }
  }

  override def update(): Unit = println(controller)
}

