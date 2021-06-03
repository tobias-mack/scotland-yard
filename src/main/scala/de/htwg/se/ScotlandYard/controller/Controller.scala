package de.htwg.se.ScotlandYard.controller

import de.htwg.se.ScotlandYard.model.{Board, BoardDefaultStrategy, BoardStrategyTemplate}
import de.htwg.se.ScotlandYard.util.{Observable, UndoManager}

import scala.sys.exit
import scala.util.Try

class  Controller(var board: Board) extends Observable{

  private val undoManager = new UndoManager
  private var order = -1
  var gameState: GameState = GameState(this)
  var playerNumber = 0
  var playerAdded = 0
  var chosenTransport = 0
  def exec(input:String): Unit = {
    gameState.handle(input)
  }
  def movePlayer(pos:Int,transport: Int): Unit = {
    board = BoardStrategyTemplate("default").movePlayer(board,pos,this.order,transport)
    if(checkWinning()){WinningState(this).handle()}
    if(checkLoosing()){LoosingState(this).handle()}
    notifyObservers()
  }
  def checkPossDest(position: Int,transport: Int):Boolean = {
    board.checkPossDest(position,transport,this.order)
  }
  def checkWinning():Boolean = {
      for(det <- board.player){
        if(!det.equals(board.player(0))){
          if(det.cell.number.equals(board.player(0).cell.number)) return true
        }
      }
    false
  }
  def checkLoosing():Boolean={
    board.checkLoosing()
  }
  def posToInt(position: String): Try[Int] = Try(position.toInt)
  def addDetective(name1: String): Unit = {
    undoManager.doStep(new AddDetectiveCommand(name1,this))
    notifyObservers()
  }
  override def toString: String = {
    board.toString
  }
  def nextPlayer(): Unit ={
    this.order = (this.order + 1) % this.board.player.size
  }
  def undo(): Unit = {
    undoManager.undoStep
    notifyObservers()
  }

  def redo(): Unit = {
    undoManager.redoStep
    notifyObservers()
  }

}
