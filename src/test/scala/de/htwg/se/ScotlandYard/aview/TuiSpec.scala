package de.htwg.se.ScotlandYard.aview

import de.htwg.se.ScotlandYard.controller.Controller
import de.htwg.se.ScotlandYard.model._
import de.htwg.se.ScotlandYard.util.Observer
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.io.ByteArrayInputStream

class TuiSpec extends AnyWordSpec with Matchers {
  "A tui" when {
    "created" should {
      val board1 = Board()
      val controller = new Controller(board1)
      val tui = new Tui(controller)
      val input = "taxi"
      val order = 0
      "process the input for transportation" in {
        tui.processInput("taxi") should be (1)
        tui.processInput("bus") should be (2)
        tui.processInput("sub") should be (3)
        tui.processInput("black") should be (4)
        tui.processInput("asldkfj") should be (-1)

        //tui.update() should be (controller.toString)
        //tui.inputMovePlayer("3",order,1) should be ()
        //tui.howManyPlayers() should be ()
      }
    }
  }

}
