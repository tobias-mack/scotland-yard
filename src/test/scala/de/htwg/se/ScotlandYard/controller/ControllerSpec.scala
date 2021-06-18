package de.htwg.se.ScotlandYard.controller

import de.htwg.se.ScotlandYard.controller.controllerBaseImpl.Controller
import de.htwg.se.ScotlandYard.model.gameComponents.Board
import de.htwg.se.ScotlandYard.util.Observer
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ControllerSpec extends AnyWordSpec with Matchers {
  val detStartTicketsTax = 10
  val mrXStartTicketsTax = 9
  "A Controller" when {
    "observed by an Observer" should {
      val controller = new Controller()
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update(): Boolean = {
          updated = true; updated
        }
      }
      controller.add(observer)
      "notify its Observer after moving Players" in {
        controller.movePlayer(1,1)
        observer.updated should be(true)
        controller.board.player(0).name should be("mrX")
        controller.board.player(0).ticket.taxi should be(mrXStartTicketsTax-1)
      }
      "notify its Observer after adding a player" in {
        controller.addDetective("newPlayer")
        observer.updated should be(true)
        controller.board.player(3).name should be("newPlayer")
        controller.board.player(3).ticket.taxi should be(detStartTicketsTax)
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
}
