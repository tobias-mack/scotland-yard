package de.htwg.se.ScotlandYard.aview

import de.htwg.se.ScotlandYard.controller.ControllerInterface
import de.htwg.se.ScotlandYard.controller.controllerBaseImpl.GameState
import de.htwg.se.ScotlandYard.util.{Observer, State, UI}

import scala.io.StdIn.readLine

case class Tui (controller: ControllerInterface) extends UI with Observer{

  controller.add(this)
  def runTui(): Unit = {
    var input: String = ""
    var state: String = ""
    do{
      input = readLine()
      state = this.processInput(input).getClass.getSimpleName
    } while( input != "q" &&
      state != "WinningState" &&
      state != "LoosingState")
  }
  override def processInput(input: String): State[GameState] = controller.exec(input)

  override def update(): Boolean = {
    println(controller)
    true
  }
}