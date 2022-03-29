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

  def n(outer: Int): mapKN.NodeT

  def nT(outer: Int): mapKNTaxi.NodeT

  def nB(outer: Int): mapKNBus.NodeT

  def nS(outer: Int): mapKNSub.NodeT

  def getNeighbours(position: Int): Set[mapKN.NodeT]

  def getNeighboursTaxi(position: Int): Set[mapKNTaxi.NodeT]

  def getNeighboursBus(position: Int): Set[mapKNBus.NodeT]

  def getNeighboursSub(position: Int): Set[mapKNSub.NodeT]

  def isPossible(set: Set[mapKN.NodeT], goToPos: Int): Boolean

  def isPossibleT(set: Set[mapKNTaxi.NodeT], goToPos: Int): Boolean

  def isPossibleB(set: Set[mapKNBus.NodeT], goToPos: Int): Boolean

  def isPossibleS(set: Set[mapKNSub.NodeT], goToPos: Int): Boolean

  def checkPossDest(position: Int, transport: Int, currentOrder: Int): Boolean

  def checkLoosing(): Boolean

  def checkTransport(transport: Int, currentOrder: Int): Boolean

  def addDetective(board: BoardInterface, newName: String, cell: Cell, ticket: Ticket): Board

  def checkWinning(): Boolean