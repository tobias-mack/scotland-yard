package de.htwg.se.ScotlandYard.controller.controllerBaseImpl

import de.htwg.se.ScotlandYard.model.gameComponents.{Board, Cell, GameInformation, Ticket}
import de.htwg.se.ScotlandYard.util.Command

import scala.collection.mutable.ListBuffer

class AddDetectiveCommand(name: String, cell: Cell, ticket: Ticket, controller: Controller) extends Command :
  override def doStep(): Unit =

    controller.board = controller.board.addDetective(controller.board, name, cell, ticket)
    controller.playerAdded += 1

    if controller.playerNumber == controller.playerAdded then
      controller.gameState.nextState(TransportState(controller))

  override def undoStep(): Unit =
    val newBoard = Board(player1 = controller.board.player.dropRight(1), GameInformation(ListBuffer[Int](), 1, 1)) //not correct
    controller.playerAdded -= 1
    controller.board = newBoard
    controller.gameState.nextState(PlayerNamesState(controller))

  override def redoStep(): Unit =
    doStep()