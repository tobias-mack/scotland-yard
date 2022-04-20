package de.htwg.se.ScotlandYard.controller.controllerBaseImpl

import model.gameComponents.{Board, Cell, Ticket}
import tools.util.Command

class AddDetectiveCommand(name: String, cell: Cell, ticket: Ticket, controller: Controller) extends Command :
  override def doStep(): Unit =

    controller.board = controller.board.addDetective(Option(controller.board), name, cell, ticket)
    controller.playerAdded += 1

    if controller.playerNumber == controller.playerAdded then
      controller.gameState.nextState(TransportState(controller))

  override def undoStep(): Unit =
    val newBoard = Board(player1 = controller.board.player.dropRight(1))
    controller.playerAdded -= 1
    controller.board = Option(newBoard)
    controller.gameState.nextState(PlayerNamesState(controller))

  override def redoStep(): Unit =
    doStep()