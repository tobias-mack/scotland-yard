package de.htwg.se.ScotlandYard.aview

import de.htwg.se.ScotlandYard.controller.controllerBaseImpl.Controller
import de.htwg.se.ScotlandYard.model._
import de.htwg.se.ScotlandYard.model.gameComponents.Board
import de.htwg.se.ScotlandYard.util.Observer
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.io.ByteArrayInputStream

class TuiSpec extends AnyWordSpec with Matchers {
  "A tui" when {
    "created" should {
      val board1 = Board()
      val controller = new Controller(board1)
      controller.notifyObservers()
      val tui = new Tui(controller)
      val numberPlayers = "2"
      val player1 = "Player1"
      val player2 = "Player2"
      val transport1 = "taxi"
      val transport2 = "bus"
      val transport3 = "Black"
      val transport4 = "Sub"
      val goToPosition = "22"
      val goToPosition2 = "3"
      val goToPosition3 = "10"
      val garbage = "asdfa"
      "process the first input" in {
        tui.processInput(numberPlayers)
      }
      "then accept player names" in {
        tui.processInput(player1)
        tui.processInput(player2)
      }
      "then accept transportation" in {
        tui.processInput(transport1)
      }
      "and finally accept the goToPosition 22" in{
        tui.processInput(goToPosition)
      }
      "player 2 goes with bus to location 3" in {
        tui.processInput(transport2)
        tui.processInput(goToPosition2)
      }
      "player 1 goes with black ticket to location 10" in {
        tui.processInput(transport3)
        tui.processInput(goToPosition3)
      }
      //"process garbage" in {
        //tui.processInput(garbage)
      //}
      "player 2 goes with sub to location 10" in {
        tui.processInput(transport4)
        tui.processInput(goToPosition3)
      }

    }
  }

}
