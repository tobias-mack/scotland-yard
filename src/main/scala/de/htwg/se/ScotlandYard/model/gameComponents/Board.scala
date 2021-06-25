package de.htwg.se.ScotlandYard.model.gameComponents

import de.htwg.se.ScotlandYard.model.BoardInterface
import scalax.collection.Graph
import scalax.collection.GraphEdge.UnDiEdge
import scalax.collection.GraphPredef.EdgeAssoc
import com.google.inject.name.Named
import com.google.inject.Inject

import scala.collection.immutable.BitSet

case class Board @Inject() (@Named("DefaultPlayer") player1: Vector[Player] = Vector[Player]()) extends BoardInterface {

  val player: Vector[Player] = player1

  val mapKN: Graph[Int, UnDiEdge] = Graph(10 ~ 1, 1 ~ 20, 20 ~ 2, 1 ~ 2, 2 ~ 3, 2 ~ 5, 3 ~ 4, 4 ~ 5, 4 ~ 6, 5 ~ 6, 5 ~ 7, 5 ~ 8, 7 ~ 9,
      7~8, 8 ~ 21, 21 ~ 9, 9 ~ 13, 10 ~ 13, 9 ~ 10, 10 ~ 11, 11 ~ 12, 12 ~ 13, 12 ~ 14, 13 ~ 14,
      14 ~ 15, 15 ~ 16, 16 ~ 6, 16 ~ 7, 16 ~ 19, 16 ~ 18, 15 ~ 17, 17 ~ 18, 18 ~ 19, 19 ~ 20)
  val mapKNTaxi: Graph[Int, UnDiEdge] = Graph(10 ~ 1, 1 ~ 20, 2 ~ 3, 2 ~ 5, 3 ~ 4, 4 ~ 5, 5 ~ 6, 5 ~ 7, 5 ~ 8,
      7~8, 9 ~ 13, 10 ~ 13, 9 ~ 10, 12 ~ 13, 12 ~ 14, 13 ~ 14, 15 ~ 16, 16 ~ 6, 16 ~ 7, 16 ~ 19, 16 ~ 18, 18 ~ 19)
  val mapKNBus: Graph[Int, UnDiEdge] = Graph(10 ~ 1, 1 ~ 20, 20 ~ 2, 1 ~ 2, 4 ~ 6, 5 ~ 8,
      7~8, 8 ~ 21, 21 ~ 9, 10 ~ 11, 11 ~ 12, 12 ~ 14, 15 ~ 16, 16 ~ 6, 16 ~ 18, 15 ~ 17, 17 ~ 18)
  val mapKNSub: Graph[Int, UnDiEdge] = Graph(7 ~ 9, 14 ~ 15)

  val taxiLocations: BitSet = BitSet(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14, 15, 16, 18, 19, 20)
  val busLocations: BitSet = BitSet(1, 2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 16, 17, 18, 20, 21)
  val subLocations: BitSet = BitSet(7, 9, 14, 15)

  def checkTransport(transport: Int, currentOrder: Int): Boolean = {
    transport match{
      case 1 => taxiLocations(player(currentOrder).cell.number)
      case 2 => busLocations(player(currentOrder).cell.number)
      case 3 => subLocations(player(currentOrder).cell.number)
      case 4 => true
    }
  }

  def n(outer: Int): mapKN.NodeT = mapKN get outer
  def nT(outer: Int): mapKNTaxi.NodeT = mapKNTaxi get outer
  def nB(outer: Int): mapKNBus.NodeT = mapKNBus get outer
  def nS(outer: Int): mapKNSub.NodeT = mapKNSub get outer

  def getNeighbours(position: Int): Set[mapKN.NodeT] = n(position).diSuccessors
  def getNeighboursTaxi(position: Int): Set[mapKNTaxi.NodeT] = nT(position).diSuccessors
  def getNeighboursBus(position: Int): Set[mapKNBus.NodeT] = nB(position).diSuccessors
  def getNeighboursSub(position: Int): Set[mapKNSub.NodeT] = nS(position).diSuccessors

  def isPossible(set: Set[mapKN.NodeT], goToPos: Int): Boolean = set.exists(x => x.value == goToPos)
  def isPossibleT(set: Set[mapKNTaxi.NodeT], goToPos: Int): Boolean = set.exists(x => x.value == goToPos)
  def isPossibleB(set: Set[mapKNBus.NodeT], goToPos: Int): Boolean = set.exists(x => x.value == goToPos)
  def isPossibleS(set: Set[mapKNSub.NodeT], goToPos: Int): Boolean = set.exists(x => x.value == goToPos)

  def checkPossDest(position: Int, transport: Int, currentOrder: Int): Boolean = {
    transport match{
      case 1 =>
        val nb = getNeighboursTaxi(player(currentOrder).cell.number)
        isPossibleT(nb, position)
      case 2 =>
        val nb = getNeighboursBus(player(currentOrder).cell.number)
        isPossibleB(nb, position)
      case 3 =>
        val nb = getNeighboursSub(player(currentOrder).cell.number)
        isPossibleS(nb, position)
      case 4 =>
        val nb = getNeighbours(player(currentOrder).cell.number)
        isPossible(nb, position)
    }
  }

  def checkLoosing(): Boolean = {
    for (det <- this.player) {
      if (!det.equals(this.player(0))) {
        if (det.ticket.isEmpty) return true
      }
    }
    false
  }

  def checkWinning(): Boolean = {
    for(det <- player){
      if(!det.equals(player(0))){
        if(det.cell.number.equals(player(0).cell.number)) return true
      }
    }
    false
  }

  def addDetective(board: BoardInterface, newName: String, cell: Cell, ticket: Ticket): Board = {
    val MrX = MisterX(newName,cell,ticket)
    val newPlayer = Detective(newName,cell,ticket)
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
