package de.htwg.se.ScotlandYard
import de.htwg.se.ScotlandYard.aview.Tui
import de.htwg.se.ScotlandYard.controller.controllerBaseImpl.Controller
import de.htwg.se.ScotlandYard.model.gameComponents.Board
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.io.ByteArrayInputStream

class ScotlandYardSpec extends AnyWordSpec with Matchers {

  "ScotlandYard" when {
    val controller1 = new Controller(Board())
    val tui = new Tui(controller1)
    "take input" in {
      val in = new ByteArrayInputStream("q".getBytes())
      Console.withIn(in) {ScotlandYard.main(Array("2","taxi","q"))}
    }

    "quit" should {
      "quit" in {
        val in = new ByteArrayInputStream("q".getBytes)
        Console.withIn(in) {ScotlandYard.main(Array())}
      }
    }
  }
}
