package de.htwg.se.ScotlandYard.aview

import de.htwg.se.ScotlandYard.controller.Controller

import scala.io.StdIn.readLine

class GameStart(controller: Controller){

  def gameStart(): Unit = {
    println("___## Welcome to Scotland-Yard #___")
    println("Please enter the name of the first Detective!")
    val in = readLine()
    controller.addDetective(in,1)
    println("You may choose between 1 or 2 detectives: (2 or 3) ")
    val input = readLine()
    input match {
      case "2" =>
      case "3" => println("Please enter the name of the 2nd detective")
        val input2 = readLine()
        controller.addDetective(input2,2)
      case _ => println("Sorry, wrong Input detected... no try catch yet :D")
        System.exit(0)
    }
    println(":LET THE GAME BEGIN!")
  }

}
