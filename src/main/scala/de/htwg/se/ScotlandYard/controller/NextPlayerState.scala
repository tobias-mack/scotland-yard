package de.htwg.se.ScotlandYard.controller

import de.htwg.se.ScotlandYard.util.State

import scala.io.StdIn.readLine

case class NextPlayerState(controller: Controller) extends State[GameState] {
  override def handle(input: String, state: GameState): Unit = {
    val taxi = """(t|T)(a|A)(x|X)(i|I)"""
    val bus = "(b|B)(u|U)(s|S)"
    val subway = "(s|S)(u|U)(b|B)"
    val black = "(b|B)(l|L)(a|A)(c|C)(k|K)"
    input match {
      case s if s.matches(taxi) => controller.chosenTransport = 1
      case s if s.matches(bus) => controller.chosenTransport = 2
      case s if s.matches(subway) => controller.chosenTransport = 3
      case s if s.matches(black) => controller.chosenTransport = 4
      case _ => controller.chosenTransport = -1
    }
    if(controller.chosenTransport != -1) {
      state.nextState(MoveToState(controller))
      println("where to ?")
    }
    else state.nextState(UnknownCommandState(controller))
    controller.nextPlayer()
  }

}
