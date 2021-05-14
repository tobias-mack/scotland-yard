package de.htwg.se.ScotlandYard.model

trait BoardStrategyTemplate {
  def movePlayer(board: Board, pos: Int, playerNumber: Int,transport: Int): Board
  def addDetective(board: Board, newName: String): Board
}
