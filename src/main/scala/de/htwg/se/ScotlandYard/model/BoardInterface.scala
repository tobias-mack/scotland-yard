package de.htwg.se.ScotlandYard.model

import de.htwg.se.ScotlandYard.model.gameComponents.{Board, Cell, Player}
import de.htwg.se.ScotlandYard.util.Observable
import scalax.collection
import scalax.collection.Graph
import scalax.collection.GraphEdge.UnDiEdge

trait BoardInterface extends Observable {

  def cell: Vector[Cell]
  def player: Vector[Player]
  val mapKN: Graph[Int, UnDiEdge]
  def n(outer: Int): mapKN.NodeT
  def getNeighbours(position: Int): Set[mapKN.NodeT]
  def isPossible(set: Set[mapKN.NodeT], goToPos: Int): Boolean
  def checkPossDest(position: Int, transport: Int, currentOrder: Int): Boolean
  def checkLoosing(): Boolean
  def addDetective(board: BoardInterface, newName: String): Board







}
