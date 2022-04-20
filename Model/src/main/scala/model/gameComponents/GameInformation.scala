package model.gameComponents

import scala.collection.mutable.ListBuffer

case class GameInformation (
                            val travelLog: ListBuffer[Int] = ListBuffer[Int](),
                            val revealCounter: Int = 3,
                            val currentPlayer: Int = 1
                            )
