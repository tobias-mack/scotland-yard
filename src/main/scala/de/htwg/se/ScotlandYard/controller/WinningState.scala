package de.htwg.se.ScotlandYard.controller

import scala.sys.exit

case class WinningState(controller: Controller) {
   def handle(): Unit = {
     println("WINNER WINNER CHICKEN DINNER")
     exit(0)
  }
}

