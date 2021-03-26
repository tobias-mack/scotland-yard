package de.htwg.se.ScotlandYard

import de.htwg.se.ScotlandYard.model.Player

object ScotlandYard {
  def main(args: Array[String]): Unit = {
    val student = Player("Your Name")
    println("Hello, " + student.name)
  }
}
