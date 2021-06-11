package de.htwg.se.ScotlandYard

import de.htwg.se.ScotlandYard.aview.guiStates.guiStarter
import de.htwg.se.ScotlandYard.aview.{GUI, Tui}
import de.htwg.se.ScotlandYard.controller.Controller
import de.htwg.se.ScotlandYard.model.Board
import de.htwg.se.ScotlandYard.util.UI

import scala.io.StdIn.readLine
import scala.util.{Failure, Success,Try}

object ScotlandYard {
  val controller = new Controller(Board())
  val tui: UI = new Tui(controller)
  val gui: UI = new GUI(controller)
  controller.notifyObservers()

  def main(args: Array[String]): Unit = {
    Try(guiStarter("gui",controller)) match {
      case Success(_) => println("Thank you for playing. Bye.")
      case Failure(v) => println("Could not create GUI" + v.getMessage + v.getCause)
    }
    /*
    var input: String = ""
    println("|==== Welcome to Scotland-Yard! ====|\n" +
            "|   How many players want to play?  |\n" +
            "|   Type a number between 2-5: \n" +
            "|   Then type in the player names...")
    while (true) {
      input = readLine()
      if (input == "q") {return}
      tui.processInput(input)
    }*/
  }
}