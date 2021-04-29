package de.htwg.se.ScotlandYard.controller

import de.htwg.se.ScotlandYard.model.Board
import de.htwg.se.ScotlandYard.util.Observable

class  Controller(var board: Board) extends Observable{

  def moveDetective(pos: Int): Unit = {
    board = board.moveDetective(board,pos)
    notifyObservers()
  }
  def moveMRX(pos: Int): Unit = {
    board = board.moveMRX(board,pos)
    notifyObservers()
  }

  override def toString: String = {
    board.toString
  }


}
