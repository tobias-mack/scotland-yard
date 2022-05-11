package de.htwg.se.ScotlandYard.aview

import de.htwg.se.ScotlandYard.controller.ControllerInterface
import de.htwg.se.ScotlandYard.controller.controllerBaseImpl.{GameState, State}
import de.htwg.se.ScotlandYard.util.Observer

import scala.io.StdIn.readLine

case class Tui(controller: ControllerInterface) extends UI with Observer :

  controller.add(this)

  def runTui(): Unit =
    var input: String = ""
    var state: String = ""
    while
      input != "q" && state != "WinningState" && state != "LoosingState"
    do
      input = readLine()
      state = this.processInput(input).getClass.getSimpleName

  override def processInput(input: String): State[GameState] = controller.exec(input)

  override def update(): Boolean =
    println(controller)
    true