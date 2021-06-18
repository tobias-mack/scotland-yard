package de.htwg.se.ScotlandYard.model.gameComponents

import de.htwg.se.ScotlandYard.model.BoardInterface
import scalax.collection.Graph
import scalax.collection.GraphEdge.{UnDiEdge, ~}
import scalax.collection.GraphPredef.EdgeAssoc
import com.google.inject.name.{Named, Names}
import com.google.inject.{Guice, Inject}
import net.codingwell.scalaguice.InjectorExtensions._


case class Board @Inject() (@Named("DefaultPlayer") player1: Vector[Player] = Vector[Player]()) extends BoardInterface {

  val player: Vector[Player] = player1

  val mapKN: Graph[Int, UnDiEdge] = Graph(10 ~ 1, 1 ~ 20, 20 ~ 2, 1 ~ 2, 2 ~ 3, 2 ~ 5, 3 ~ 4, 4 ~ 5, 4 ~ 6, 5 ~ 6, 5 ~ 7, 5 ~ 8, 7 ~ 9,
    8 ~ 21, 21 ~ 9, 9 ~ 13, 10 ~ 13, 9 ~ 10, 10 ~ 11, 11 ~ 12, 12 ~ 13, 12 ~ 14, 13 ~ 14,
    14 ~ 15, 15 ~ 16, 16 ~ 6, 16 ~ 7, 16 ~ 19, 16 ~ 18, 15 ~ 17, 17 ~ 18, 18 ~ 19, 19 ~ 20)

  def n(outer: Int): mapKN.NodeT = mapKN get outer

  def getNeighbours(position: Int): Set[mapKN.NodeT] = n(position).diSuccessors

  def isPossible(set: Set[mapKN.NodeT], goToPos: Int): Boolean = set.exists(x => x.value == goToPos)

  def checkPossDest(position: Int, transport: Int, currentOrder: Int): Boolean = {
    val nb = getNeighbours(player(currentOrder).cell.number)
    isPossible(nb, position)
  }

  def checkLoosing(): Boolean = {
    for (det <- this.player) {
      if (!det.equals(this.player(0))) {
        return det.ticket.isEmpty()
      }
    }
    false
  }

  def addDetective(board: BoardInterface, newName: String): Board = {
    val MrX = MisterX(name = newName)
    val newPlayer = Detective(name = newName)
    val newBoard = Board(player1 = if (board.player.isEmpty) {
      board.player :+ MrX
    } else {
      board.player :+ newPlayer
    })
    newBoard
  }

  override def toString: String = {
    val board = new StringBuilder("  Board: ")
    board.setLength(board.length() - 2)
    val Statement = new StringBuilder()
    for (n <- player) {
      Statement.append("  \u001b[30m" + n.name + " \u001b[0mis at \u001b[34mposition " + n.cell.number + " \u001b[0mand has\u001b[33m " +
        n.ticket.taxi + " Taxi \u001b[0mtickets,\u001b[32m" + n.ticket.bus + " Bus \u001b[0mtickets, \u001b[31m" +
        n.ticket.subway + " Subway \u001b[0mtickets" + "; \n")
    }
    Statement.append(board)
    Statement.toString()
  }
}
