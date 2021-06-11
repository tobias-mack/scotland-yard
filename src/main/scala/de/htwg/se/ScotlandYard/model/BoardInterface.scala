package de.htwg.se.ScotlandYard.model

import de.htwg.se.ScotlandYard.model.gameComponents.{Cell, Player}
import de.htwg.se.ScotlandYard.util.Observable
import scalax.collection
import scalax.collection.Graph
import scalax.collection.GraphEdge.UnDiEdge

trait BoardInterface extends Observable {

  val cell: Cell
  val player: Player
  def mapKN: Graph[Int, UnDiEdge]



  def checkPossDest(position: Int, transport: Int, currentOrder: Int): Boolean
  def checkLoosing(): Boolean





}
