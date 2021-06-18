
package de.htwg.se.ScotlandYard.controller
import de.htwg.se.ScotlandYard.controller.controllerBaseImpl.{Controller, MoveToState, NextPlayerState, PlayerNamesState, StartState}
import de.htwg.se.ScotlandYard.model.gameComponents.Board
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class StatemachineSpec extends AnyWordSpec with Matchers {
  "A Statemachine" when {
    "created" should {
      val board1 = Board()
      val controller1 = new Controller()
      val board2 = Board()
      val controller2=new Controller()
      "win" in {
        controller1.gameState.state should be(StartState(controller1))
        controller1.gameState.handle("2")
        controller1.gameState.state should be(PlayerNamesState(controller1))
        controller1.gameState.handle("name1")
        controller1.gameState.handle("name2")
        controller1.gameState.state should be(NextPlayerState(controller1))
        controller1.gameState.handle("taXi")
        controller1.gameState.state should be(MoveToState(controller1))
        controller1.gameState.handle("4")
        controller1.gameState.handle("bus")
        controller1.gameState.handle("2")
        controller1.gameState.handle("black")
        controller1.gameState.handle("3")
        controller1.gameState.handle("unknownTransport")
        controller1.gameState.handle("unknownTransport2")
        controller1.gameState.handle("sub")
        controller1.gameState.handle("33")
        controller1.gameState.handle("unknown number")
        controller1.gameState.handle("3")
        //controller1.gameState.handle("unknownCommand") //should be (controller1.gameState.nextState(NextPlayerState(controller1)))
        //controller1.gameState.handle("unknown")
      }
      "loose" in { //TODO let player loose -> tickets == 0
        controller2.gameState.handle("2")
        controller2.gameState.handle("name1")
        controller2.gameState.handle("name2")
        controller2.gameState.handle("taXi")
        controller2.gameState.handle("4")
        controller2.gameState.handle("bus")
        controller2.gameState.handle("2")
      }
    }
  }
}
