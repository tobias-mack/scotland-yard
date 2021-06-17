package de.htwg.se.ScotlandYard.aview.GUI

import scala.collection.immutable.HashMap

object StationLocater {
  val hashMap: HashMap[String, Point] = HashMap("1"->Point(-70,-440),
                        "2"->Point(370,-50),
                        "3"->Point(430,-40),
                        "4"->Point(410,100),
                        "5"->Point(330,100),
                        "6"->Point(210,220),
                        "7"->Point(140,170),
                        "8"->Point(165,0),
                        "9"->Point(-310,-160),
                        "10"->Point(-390,-250),
                        "11"->Point(-630,-320),
                        "12"->Point(-580,-140),
                        "13"->Point(-380,-160),
                        "14"->Point(-480,-80),
                        "15"->Point(-100,170),
                        "16"->Point(100,280),
                        "17"->Point(-100,350),
                        "18"->Point(-15,380),
                        "19"->Point(100,350),
                        "20"->Point(400,-440),
                        "21"->Point(140,-170)
  )
  def findXYpos(goToLoc: String): Option[Point] ={
    hashMap.get(goToLoc)
  }
}

case class Point(x: Float, y: Float)