package de.htwg.se.ScotlandYard.model

import de.htwg.se.ScotlandYard.model.gameComponents.{Board, Cell, Player, Ticket}
import de.htwg.se.ScotlandYard.util.Observable
import scalax.collection.Graph
import scalax.collection.GraphEdge.UnDiEdge

trait BoardInterface extends Observable :

  def player: Vector[Player]

  val mapKN: Graph[Int, UnDiEdge]
  val mapKNTaxi: Graph[Int, UnDiEdge]
  val mapKNBus: Graph[Int, UnDiEdge]
  val mapKNSub: Graph[Int, UnDiEdge]

  def n(map: Graph[Int, UnDiEdge], outer: Int): map.NodeT

  def getNeighbours(map: Graph[Int, UnDiEdge], position: Int): Set[map.NodeT]

  def isPossible(map: Graph[Int, UnDiEdge], set: Set[map.NodeT], goToPos: Int): Boolean

  def checkPossDest(position: Int, transport: Int, currentOrder: Int): Boolean

  def checkLoosing(): Boolean

  def checkTransport(transport: Int, currentOrder: Int): Boolean

  def addDetective(board: BoardInterface, newName: String, cell: Cell, ticket: Ticket): Board

  def checkWinning(): Boolean