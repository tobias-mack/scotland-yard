package de.htwg.se.ScotlandYard.controller

import de.htwg.se.ScotlandYard.model.Board
import de.htwg.se.ScotlandYard.util.Observable

class  Controller(var board: Board) extends Observable{

  def movePlayer(pos: Int, playerNumber: Int,transport: Int): Unit = {
    board = board.movePlayer(board,pos,playerNumber,transport)
    notifyObservers()
  }
  def addDetective(name1: String): Unit = {
    board = board.addDetective(board, name1)
    notifyObservers()
  }

  override def toString: String = {
    board.toString
  }


}
