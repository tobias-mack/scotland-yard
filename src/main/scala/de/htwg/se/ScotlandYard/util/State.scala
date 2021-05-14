package de.htwg.se.ScotlandYard.util

object State {
  var state = println("")
  def handle(gs: GameState) = {
    gs match {
      case a: unknownCommandEvent => state = unknownCommandEvent
      case b: startEvent => state = startEvent
      case c: nextPlayerEvent => state = nextPlayerEvent
    }
  }
  def unknownCommandEvent:Unit = println("invalid command")
  def startEvent:Unit = println("MrX, you'll beginn, start by typing your means of transport")
  def nextPlayerEvent:Unit = println("Next Player, its your turn")

}
