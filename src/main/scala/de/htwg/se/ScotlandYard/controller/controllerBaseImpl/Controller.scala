package de.htwg.se.ScotlandYard.controller.controllerBaseImpl

import com.google.inject.{Guice, Inject, Injector}
import de.htwg.se.ScotlandYard.ScotlandYardModule
import de.htwg.se.ScotlandYard.controller.ControllerInterface
import fileIOComponent.FileIOInterface
import model.BoardInterface
import model.gameComponents.*
import net.codingwell.scalaguice.InjectorExtensions.*
import tools.util.UndoManager

import scala.collection.mutable.ListBuffer
import scala.util.Try

class Controller @Inject()() extends ControllerInterface :

  var board: BoardInterface = Board()
  var loadStatus = false
  private val undoManager = new UndoManager
  var order: Int = 0
  var gameState: GameState = GameState(this)
  var playerNumber = 0
  var playerAdded = 0
  var chosenTransport = 0
  var travelLog = new ListBuffer[Int]
  var revealCounter = 3
  val numOfPlayers: Range = 2 to 5

  def exec(input: String): State[GameState] =
    gameState.handle(input)

  def updateReveal(transport: Int): Unit =
    if this.order == 0 then
      travelLog += transport
      if this.revealCounter != 0 then
        this.revealCounter -= 1
      else
        this.revealCounter = 2

  def checkReveal(): Boolean =
    this.order != 0 && revealCounter == 0

  def movePlayer(pos: Int, transport: Int): Unit =
    board = BoardStrategyTemplate("default").movePlayer(board, pos, this.order, transport)
    updateReveal(transport)
    if checkWinning() then
      WinningState(this).handle("", gameState)
      this.gameState.nextState(WinningState(this))

    if checkLoosing() then
      LoosingState(this).handle("", gameState)
      this.gameState.nextState(LoosingState(this))

    notifyObservers()

  def checkTransport(transport: Int, currentOrder: Int): Boolean =
    board.checkTransport(transport, currentOrder)

  def checkPossDest(position: Int, transport: Int): Boolean =
    board.checkPossDest(position, transport, this.order)

  def checkWinning(): Boolean =
    board.checkWinning()

  def checkLoosing(): Boolean =
    board.checkLoosing()

  def posToInt(position: String): Try[Int] = Try(position.toInt)

  def addDetective(name1: String): Unit =
    addDetective(name1, Cell(), Ticket())

  def addDetective(name1: String, cell: Cell, ticket: Ticket): Unit =
    if loadStatus then
      undoManager.doStep(new AddDetectiveCommand(name1, cell, ticket, this))
    else if this.playerAdded == 0 then
      undoManager.doStep(new AddDetectiveCommand(name1, Cell(5), Ticket(9, 5, 3, 5), this))
    else
      undoManager.doStep(new AddDetectiveCommand(name1, Cell(1), Ticket(10, 8, 4), this))
    notifyObservers()

  override def toString: String =
    board.toString

  def nextPlayer(): Unit =
    this.order = (this.order + 1) % this.board.player.size

  def undo(): Unit =
    undoManager.undoStep()
    notifyObservers()

  def redo(): Unit =
    undoManager.redoStep()
    notifyObservers()

  val injector: Injector = Guice.createInjector(new ScotlandYardModule)
  val fileIO: FileIOInterface = injector.getInstance(classOf[FileIOInterface])

  def save(): Unit =
    fileIO.save(this.board)

  def load(): Unit =
    if playerNumber != 0 then
      for
        _ <- 0 until this.playerAdded
      yield this.undo()
    travelLog.clear()
    val player: Vector[Player] = fileIO.load(this.board)
    loadStatus = true
    for
      i <- 0 until this.playerNumber
    yield this.addDetective(player(i).name, player(i).cell, player(i).ticket)