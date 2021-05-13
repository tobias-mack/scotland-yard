
package de.htwg.se.ScotlandYard.aview
import de.htwg.se.ScotlandYard.controller.Controller
import de.htwg.se.ScotlandYard.util.Observer

import scala.io.StdIn.readLine

class Tui (controller: Controller) extends Observer{
  controller.add(this)

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

  def processInputLine(input: String, order: Int): Unit = {
    val taxi = """(t|T)(a|A)(x|X)(i|I)"""
    val bus = "(b|B)(u|U)(s|S)"
    val subway = "(s|S)(u|U)(b|B)"
    val black = "(b|B)(l|L)(a|A)(c|C)(k|K)"
    input match {
      case s if s.matches(taxi) => println("where to?")
                      val pos = readLine().toInt
                      controller.movePlayer(pos,order,1)
      case s if s.matches(bus) => println("where to?")
                      val pos = readLine().toInt
                      controller.movePlayer(pos,order,2)
      case s if s.matches(subway) => println("where to?")
                      val pos = readLine().toInt
                      controller.movePlayer(pos,order,3)
      case s if s.matches(black) => println("where to?")
                      val pos = readLine().toInt
                      controller.movePlayer(pos,order,4)
      case _ =>
        System.exit(0)
        scala.io.StdIn.readLine("command does not exist. Try again.\n")
    }
  }

  override def update(): Unit = println(controller)
}

