package de.htwg.se.ScotlandYard.controller

import de.htwg.se.ScotlandYard.model.{Board, BoardDefaultStrategy}
import de.htwg.se.ScotlandYard.util.{Observable, UndoManager}

class  Controller(var board: Board) extends Observable{

  private val undoManager = new UndoManager
  private var order = -1
  var gameState: GameState = GameState(this)
  var playerNumber = 0
  var playerAdded = 0
  var chosenTransport = 0
  def exec(input:String): Unit = {
    gameState.handle(input)
    //nextPlayer()
  }

  def movePlayer(pos: Int,transport: Int): Unit = {
    board = (new BoardDefaultStrategy).movePlayer(board,pos,this.order,transport)  //board = board.movePlayer(board,pos,playerNumber,transport)
    notifyObservers()
  }
  def addDetective(name1: String): Unit = {
    board = board.addDetective(board, name1)
    playerAdded+=1
    if(playerNumber==playerAdded) gameState.nextState(NextPlayerState(this))
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
