package de.htwg.se.ScotlandYard.controller

import de.htwg.se.ScotlandYard.controller.controllerBaseImpl.{GameState, State}
import de.htwg.se.ScotlandYard.model.BoardInterface
import de.htwg.se.ScotlandYard.model.gameComponents.{Cell, Ticket}
import de.htwg.se.ScotlandYard.util.Observable

import scala.collection.mutable.ListBuffer
import scala.util.Try

trait ControllerInterface extends Observable :

  val numOfPlayers: Range
  var order: Int
  var revealCounter: Int
  var travelLog: ListBuffer[Int]
  var playerNumber: Int

  def playerAdded: Int

  def exec(input: String): State[GameState]

  def movePlayer(pos: Int, transport: Int): Unit

  def checkPossDest(position: Int, transport: Int): Boolean

  def checkWinning(): Boolean

  def checkLoosing(): Boolean

  def checkTransport(transport: Int, currentOrder: Int): Boolean

  def checkReveal(): Boolean

  def updateReveal(transport: Int): Unit

  def posToInt(position: String): Try[Int]

  def addDetective(name1: String, cell: Cell, ticket: Ticket): Unit

  def addDetective(name1: String): Unit

  def nextPlayer(): Unit

  def undo(): Unit

  def redo(): Unit

  def board: BoardInterface

  def gameState: GameState

  def chosenTransport: Int

  def save(): Unit

  def load(): Unit
