
package de.htwg.se.ScotlandYard.controller
import de.htwg.se.ScotlandYard.controller.controllerBaseImpl.{Controller, MoveToState, NextPlayerState, PlayerNamesState, StartState}
import de.htwg.se.ScotlandYard.model.gameComponents.{Board, Cell, Player, Ticket}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class StatemachineSpec extends AnyWordSpec with Matchers {
  "A Statemachine" when {
    "created" should {
      val controller1 = new Controller()
      val controller2=new Controller()
      "is able to ignore wrong inputs in StartState" in{
        controller1.gameState.state should be(StartState(controller1))
        controller1.gameState.handle("wrongInput")
        controller1.gameState.handle("2")
      }
      "is able to undo and redo a player name" in {
        controller1.gameState.state should be(PlayerNamesState(controller1))
        controller1.gameState.handle("undo")
        controller1.gameState.handle("redo")
        controller1.gameState.handle("name1")
        controller1.gameState.handle("undo")
        controller1.gameState.handle("redo")
        controller1.gameState.handle("name2")
        controller1.gameState.state should be(NextPlayerState(controller1))
      }
      "is able to differ between right and wrong inputs for transport and destination" in {
        controller1.gameState.handle("taXi")
        controller1.gameState.state should be(MoveToState(controller1))
        controller1.gameState.handle("4")
        controller1.gameState.handle("bus")
        controller1.gameState.handle("20")
        controller1.gameState.handle("black")
        controller1.gameState.handle("3")
        controller1.gameState.handle("unknownTransport")
        controller1.gameState.handle("bus")
        controller1.gameState.handle("2")
        //controller1.gameState.handle("unknown number")
      }
      "win" in {
        controller1.gameState.handle("taxi")
        controller1.gameState.handle("2")
      }
      "loose" in {
        controller2.gameState.handle("2")
        controller2.gameState.handle("name1")
        controller2.gameState.handle("name2")

        val newPlay = controller2.board.player.updated(1, controller2.board.player(1).setCell(
          controller2.board.player(1),Cell(1),Ticket(1))) :Vector[Player]
        val newBoard = Board(newPlay)
        controller2.board = newBoard
        controller2.gameState.handle("taXi")
        controller2.gameState.handle("4")
        controller2.gameState.handle("taxi")
        controller2.gameState.handle("20")
        println(controller2.board.toString)
      }
    }
  }
}
