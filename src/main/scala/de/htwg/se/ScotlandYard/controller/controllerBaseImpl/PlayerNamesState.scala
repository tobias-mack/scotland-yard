package de.htwg.se.ScotlandYard.controller.controllerBaseImpl

case class PlayerNamesState(controller: Controller) extends State[GameState] :
  override def handle(input: String, state: GameState): Unit =
    input match
      case "undo" =>
        controller.undo()
        println("Undo done.")
      case "redo" =>
        controller.redo()
        println("Redo done.")
      case _ => println(s"Player ${controller.playerAdded + 1} accepted. ")
        controller.addDetective(input)