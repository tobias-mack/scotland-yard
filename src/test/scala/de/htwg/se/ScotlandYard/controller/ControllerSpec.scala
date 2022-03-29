package de.htwg.se.ScotlandYard.controller

import de.htwg.se.ScotlandYard.controller.controllerBaseImpl.Controller
import de.htwg.se.ScotlandYard.model.gameComponents.Board
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ControllerSpec extends AnyWordSpec with Matchers :
  val detStartTicketsTax = 10
  val mrXStartTicketsTax = 9
  "A Controller" when {
    "observed by an Observer" should {
      val controller = new Controller()
      "notify its Observer after adding a player" in {
        controller.addDetective("newPlayer1")
        controller.addDetective("newPlayer2")
        controller.board.player(0).name should be("newPlayer1")
        controller.board.player(1).name should be("newPlayer2")
        controller.board.player(0).ticket.taxi should be(mrXStartTicketsTax)
        controller.board.player(1).ticket.taxi should be(detStartTicketsTax)
      }
      "undo the added player and redo should reverse undo" in {
        controller.undo()
        controller.redo()
        controller.board.player(1).name should be("newPlayer2")
      }
      "notify its Observer after moving Players" in {
        controller.movePlayer(1,1)
        controller.board.player(0).ticket.taxi should be(mrXStartTicketsTax-1)
      }

    }
    "Printed out" should {
      val board1 = Board()
      val controller1 = new Controller()
      "to a String" in {
        controller1.toString should be (board1.toString)
      }
    }
  }