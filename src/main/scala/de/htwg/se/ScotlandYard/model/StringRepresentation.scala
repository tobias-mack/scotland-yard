package de.htwg.se.ScotlandYard.model

import scala.util.Random

case class StringRepresentation() {
  def printBoard(): Unit = {
    val board =
      """
________________________________________________
|                Scotland Yard                 |
|                                              |
| (10)\        ___________(12)                 |
|      \      /                                |
|       \    /                                 |
|        (11)______(14)___________________\    |
|         /                               \(13)|
|        /                                  /  |
|       /                                  /   |
| (15)_/                                  /    |
|    \                                   /     |
|     \                                 /      |
|      \                    (18)_______/       |
|       \          ________/                   |
|        \(17)____/                            |
|                                              |
|______________________________________________|
"""
    println(board)
  }

  def printTicket(): Unit = {
    val ticket =
      """
__________________________
|                        |
|     ︵︵︵︵︵︵︵︵      |
| ((((     BUS     ))))  |
|     ︶︶︶︶︶︶︶︶      |
|________________________|
        """
    println(ticket)
  }

  def playerName(name: String): String = {
    val name1 = name.toLowerCase()
    val ret = name1.capitalize
    ret
  }

  def misterX(): String = {
    println("Please enter your player count")
    val userInput = scala.io.StdIn.readLine()
    if(userInput.length > 1) {
      "9 Players are the maximum"
    }
    else {
      val inputInt = userInput.toInt
      val r = new Random()
      val r1 = 1 + r.nextInt((inputInt - 1) + 1)
      val ret = "Player " + r1 + " will be MrX"
      ret
    }
  }
}




