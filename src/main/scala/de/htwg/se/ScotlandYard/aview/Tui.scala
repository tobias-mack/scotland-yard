
package de.htwg.se.ScotlandYard.aview
import de.htwg.se.ScotlandYard.controller.Controller
import de.htwg.se.ScotlandYard.util.Observer

import scala.io.StdIn.readLine

class Tui (controller: Controller) extends Observer{
  controller.add(this)

  def processInputLine(input: String, order: Int): Unit = {
    input match {
      case "Taxi" => println("where to?")
                      val pos = readLine().toInt
                      controller.movePlayer(pos,order)
      case "Bus" => println("where to?")
                      val pos = readLine().toInt
                      controller.movePlayer(pos,order)
      case "Sub" => println("where to?")
                      val pos = readLine().toInt
                      controller.movePlayer(pos,order)
      case "black" => println("where to?")
                      val pos = readLine().toInt
                      controller.movePlayer(pos,order)
      case _ =>
        scala.io.StdIn.readLine("command does not exist. Try again.\n")
    }
  }

  override def update(): Unit = println(controller)
}

