package de.htwg.se.ScotlandYard.controller.controllerBaseImpl

import com.google.inject.{Guice, Inject, Injector}
import de.htwg.se.ScotlandYard.ScotlandYardModule
import de.htwg.se.ScotlandYard.controller.ControllerInterface
import fileIOComponent.FileIOInterface
import model.BoardInterface
import model.gameComponents.*
import net.codingwell.scalaguice.InjectorExtensions.*
import tools.util.UndoManager
import akka.http.scaladsl.server.Directives.{complete, concat, get, path}
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives.*
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCode, HttpMethods, HttpResponse, HttpRequest}
import akka.http.scaladsl.server.{ExceptionHandler, Route}

import akka.http.scaladsl.unmarshalling.Unmarshal

import play.api.libs.json.{JsValue, Json}

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

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

  //val injector: Injector = Guice.createInjector(new ScotlandYardModule)
  //val fileIO: FileIOInterface = injector.getInstance(classOf[FileIOInterface])
  val fileIOServer = "http://localhost:8081/fileio"

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
    board = BoardStrategyTemplate("default").movePlayer(board, pos, this.order, transport, travelLog, revealCounter)
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

  def save(): Unit =
    //fileIO.save(this.board)
    implicit val system = ActorSystem(Behaviors.empty, "SingleRequest")

    implicit val executionContext = system.executionContext

    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(
      method = HttpMethods.POST,
      uri = fileIOServer + "/save",
      entity = board.toJsonString
    ))
    notifyObservers()

  def load(): Unit =
    if playerNumber != 0 then
      for
        _ <- 0 until this.playerAdded
      yield this.undo()
    travelLog.clear()

    //val board: Board = fileIO.load()
    //this.board = board

    implicit val system = ActorSystem(Behaviors.empty, "SingleRequest")

    implicit val executionContext = system.executionContext

    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = fileIOServer + "/load"))

    responseFuture
      .onComplete {
        case Failure(_) => sys.error("Failed getting Json")
        case Success(value) => {
          Unmarshal(value.entity).to[String].onComplete {
            case Failure(_) => sys.error("Failed unmarshalling")
            case Success(value) => {
              val loadedBoard = board.jsonToBoard(value)
              this.board = loadedBoard
              notifyObservers()
            }
          }
        }
      }
    notifyObservers()


    updateController()

  def updateController(): Unit =
    this.travelLog = this.board.gameInfo.travelLog
    this.revealCounter = this.board.gameInfo.revealCounter
    this.order = this.board.gameInfo.currentPlayer
    this.gameState.nextState(TransportState(this))
    this.playerNumber = this.board.player.size
    this.playerAdded = this.board.player.size

