package de.htwg.se.ScotlandYard.aview

import de.htwg.se.ScotlandYard.controller.Controller

import scala.collection.immutable.{HashMap, HashSet}

object StationLocater {
  val hashMap = HashMap("1"->Point(-70,-440),
                        "2"->Point(370,-50)
  )
  def findXYpos(goToLoc: String): Option[Point] ={
    hashMap.get(goToLoc)
  }
}

case class Point(x: Float, y: Float)