/*
package de.htwg.se.ScotlandYard.controller
import de.htwg.se.ScotlandYard.controller.controllerBaseImpl.Controller
import de.htwg.se.ScotlandYard.model.gameComponents.Board
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class StatemachineSpec extends AnyWordSpec with Matchers {
  "A Statemachine" when {
    "created" should {
      val board1 = Board()
      val controller1 = new Controller(board1)
      val board2 = Board()
      val controller2=new Controller(board2)
      "win" in {
        controller1.gameState.handle("2")
        controller1.gameState.handle("name1")
        controller1.gameState.handle("name2")
        controller1.gameState.handle("taXi")
        controller1.gameState.handle("4")
        controller1.gameState.handle("bus")
        controller1.gameState.handle("2")
        controller1.gameState.handle("black")
        controller1.gameState.handle("3")
        controller1.gameState.handle("unknownTransport")
        controller1.gameState.handle("unknownPosition")
        controller1.gameState.handle("sub")
        controller1.gameState.handle("3")
        //controller1.gameState.handle("unknownCommand") //should be (controller1.gameState.nextState(NextPlayerState(controller1)))
        //controller1.gameState.handle("unknown")
      }/*
      "loose" in { //TODO let player loose -> tickets == 0
        controller2.gameState.handle("2")
        controller2.gameState.handle("name1")
        controller2.gameState.handle("name2")
        controller1.gameState.handle("taXi")
        controller1.gameState.handle("4")
        controller1.gameState.handle("bus")
        controller1.gameState.handle("2")

      }*/
    }
  }
}*/
