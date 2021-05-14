package de.htwg.se.ScotlandYard.aview

import de.htwg.se.ScotlandYard.controller.Controller
import de.htwg.se.ScotlandYard.model._
import de.htwg.se.ScotlandYard.util.Observer
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.io.ByteArrayInputStream

class TuiSpec extends AnyWordSpec with Matchers {
  /*  val detStartTicketsTax = 10
  val mrXStartTicketsTax = 9
  "A Controller" when {
    "observed by an Observer" should {
      val board = Board(Vector[Cell](Cell(1),Cell(2),Cell(3)),Vector[Player](MisterX("mrX"),Detective("pl1"),Detective("pl2")))
      val controller = new Controller(board)
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update(): Unit = updated = true
      }
      controller.add(observer)
      "notify its Observer after moving Players" in {
        controller.movePlayer(1,0,1)
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
  }*/
  "A tui" when {
    "created" should {
      val board1 = Board()
      val controller = new Controller(board1)
      val tui = new Tui(controller)
      val input = "taxi"
      val order = 0
      "process the input for transportation" in {
        tui.processInputLine(input) should be (1)
        //tui.inputMovePlayer("3",order,1) should be ()
        //tui.howManyPlayers() should be ()
      }
    }
  }

}
