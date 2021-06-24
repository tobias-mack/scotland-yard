package de.htwg.se.ScotlandYard.controller

import de.htwg.se.ScotlandYard.controller.controllerBaseImpl.GameState
import de.htwg.se.ScotlandYard.model.BoardInterface
import de.htwg.se.ScotlandYard.model.gameComponents.{Board, Cell, Ticket}
import de.htwg.se.ScotlandYard.util.{Observable, UndoManager}

import scala.util.Try

trait ControllerInterface extends Observable{

  def exec(input:String): Unit
  def movePlayer(pos:Int, transport: Int): Unit
  def checkPossDest(position: Int,transport: Int):Boolean
  def checkWinning():Boolean
  def checkLoosing():Boolean
  def checkTransport(transport: Int, currentOrder: Int): Boolean
  def posToInt(position: String): Try[Int]
  def addDetective(name1: String, cell: Cell, ticket:Ticket): Unit
  def addDetective(name1: String): Unit
  def nextPlayer(): Unit
  def undo(): Unit
  def redo(): Unit
  def board: BoardInterface
  def order: Int
  def gameState: GameState
  def playerNumber: Int
  def playerAdded: Int
  def chosenTransport: Int
  def save(): Unit
  def load(): Unit




}
