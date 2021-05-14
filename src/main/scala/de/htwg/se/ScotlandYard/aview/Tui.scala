
package de.htwg.se.ScotlandYard.aview
import de.htwg.se.ScotlandYard.controller.Controller
import de.htwg.se.ScotlandYard.util.Observer

import scala.io.StdIn.readLine

class Tui (controller: Controller) extends Observer{
  controller.add(this)

  def gameStart(): Unit = {
    val playerNumber = howManyPlayers()
    val detectives: Unit = Vector.tabulate(playerNumber) {
      n => controller.addDetective(
        if(n==0) {readLine(s"Mister X, type your name: ")}
        else {readLine(s"Player ${n + 1}, type your name: ")})
    }
    println(":LET THE GAME BEGIN!")
    println("Mr.X starts...type in: [means of transport]")
  }

  def processInputLine(input: String): Int = {
    val taxi = """(t|T)(a|A)(x|X)(i|I)"""
    val bus = "(b|B)(u|U)(s|S)"
    val subway = "(s|S)(u|U)(b|B)"
    val black = "(b|B)(l|L)(a|A)(c|C)(k|K)"
    println("where to?")
    input match {
      case s if s.matches(taxi) => 1
      case s if s.matches(bus) => 2
      case s if s.matches(subway) => 3
      case s if s.matches(black) => 4
      case _ => -1
    }
  }
  def inputMovePlayer(input: String, order: Int, transport: Int): String = {
    val inputint = input.toInt
    val out1 = "Player successfully moved to Position " + inputint + "\n"
    val out2 = "Next Player, its your turn, type in your ticket of choice"
    val output = out1 + out2
    transport match {
      case 1 => movePlayer(1, inputint, order)
        output
      case 2 => movePlayer(2, inputint, order)
        output
      case 3 => movePlayer(3, inputint, order)
        output
      case 4 => movePlayer(4, inputint, order)
        output
      case -1 =>
        System.exit(0)
        scala.io.StdIn.readLine("command does not exist. Try again.\n")

    }
  }
  def movePlayer(transport: Int, position: Int, order1: Int): Unit = {
    controller.movePlayer(position,order1,transport)
  }

  def howManyPlayers(): Int = {
    val playerNumber: Int = readLine("""||==== Welcome to Scotland-Yard! ====|
                                        ||   How many players want to play?  |
                                        ||   Type a number between 2-5: """.stripMargin).toInt
    playerNumber
  }

  override def update(): Unit = println(controller) //boolean?!?!
}

