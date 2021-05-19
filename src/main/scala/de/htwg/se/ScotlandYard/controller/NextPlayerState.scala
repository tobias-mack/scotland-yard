package de.htwg.se.ScotlandYard.controller

import de.htwg.se.ScotlandYard.util.State

import scala.io.StdIn.readLine

case class NextPlayerState(controller: Controller) extends State[GameState] {
  override def handle(input: String, state: GameState): Unit = {
    val taxi = """(t|T)(a|A)(x|X)(i|I)"""
    val bus = "(b|B)(u|U)(s|S)"
    val subway = "(s|S)(u|U)(b|B)"
    val black = "(b|B)(l|L)(a|A)(c|C)(k|K)"
    var transport = 0
    input match {
      case s if s.matches(taxi) => transport = 1
      case s if s.matches(bus) => transport = 2
      case s if s.matches(subway) => transport = 3
      case s if s.matches(black) => transport = 4
      case _ => transport = -1
    }
    val toPosition = whereTo()
    movePlayer(toPosition,transport)
    if(transport != -1) {
      state.nextState(NextPlayerState(controller))
      println("where to ?")
    }
    else state.nextState(UnknownCommandState(controller))
    println("Player was successfully moved.")
  }
  def whereTo(): Int = {
    readLine().toInt
  }
  def movePlayer(position: Int, transport: Int): Unit = {
    controller.movePlayer(position,transport)
  }
}
