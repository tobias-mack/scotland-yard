package model

import model.gameComponents.{Board, Cell, Player, Ticket, GameInformation}
import scalax.collection.Graph
import scalax.collection.GraphEdge.UnDiEdge
import tools.util.Observable

trait BoardInterface extends Observable :

  def player: Vector[Player]
  val gameInfo: GameInformation
  val mapKN: Graph[Int, UnDiEdge]
  val mapKNTaxi: Graph[Int, UnDiEdge]
  val mapKNBus: Graph[Int, UnDiEdge]
  val mapKNSub: Graph[Int, UnDiEdge]
  val transportNumToMap: Map[Int, Graph[Int, UnDiEdge]]

  def n(map: Graph[Int, UnDiEdge]): Int => map.NodeT

  def getNeighbours(map: Graph[Int, UnDiEdge], position: Int): Set[map.NodeT]

  def isPossible(map: Graph[Int, UnDiEdge], set: Set[map.NodeT], goToPos: Int): Boolean

  def checkPossDest(position: Int, transport: Int, currentOrder: Int): Boolean

  def checkLoosing(): Boolean

  def checkTransport(transport: Int, currentOrder: Int): Boolean

  def addDetective(board: BoardInterface, newName: String, cell: Cell, ticket: Ticket): Board

  def checkWinning(): Boolean