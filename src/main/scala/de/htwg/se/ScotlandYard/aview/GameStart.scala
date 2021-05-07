package de.htwg.se.ScotlandYard.aview

import de.htwg.se.ScotlandYard.controller.Controller
import de.htwg.se.ScotlandYard.model.{Cell, Detective,Ticket}

import scala.io.StdIn.readLine

class GameStart(controller: Controller){

  def gameStart(): Unit = {
    val playerNumber: Int = readLine("""||==== Welcome to Scotland-Yard! ====|
                                       ||   How many players want to play?  |
                                       ||   Type a number between 2-5: """.stripMargin).toInt
    val detectives: Unit = Vector.tabulate(playerNumber) {
      n => controller.addDetective(
        if(n==0) {readLine(s"Mister X, type your name: ")}
        else {readLine(s"Player ${n + 1}, type your name: ")})
    }
    println(":LET THE GAME BEGIN!")
    println("Mr.X starts...type in: [means of transport]")
  }

}
