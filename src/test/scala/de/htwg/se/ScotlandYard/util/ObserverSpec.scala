
package de.htwg.se.ScotlandYard.util

import de.htwg.se.ScotlandYard.aview.Tui
import de.htwg.se.ScotlandYard.controller.controllerBaseImpl.Controller
import de.htwg.se.ScotlandYard.model.gameComponents.Board
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class ObserverSpec extends AnyWordSpec with Matchers {
  "An Observer" when {
    val controller = new Controller()
    val tui = Tui(controller)
    "created" should {
      "be removed" in {
        controller.remove(tui)
      }
    }
  }

}


