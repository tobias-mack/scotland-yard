package de.htwg.se.ScotlandYard.util

import de.htwg.se.ScotlandYard.aview.Tui
import de.htwg.se.ScotlandYard.controller.Controller
import de.htwg.se.ScotlandYard.model.Board
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.security.KeyStore.TrustedCertificateEntry

class ObserverSpec extends AnyWordSpec with Matchers {
  "An Observer" when {
    val board = Board()
    val controller = new Controller(board)
    val tui = new Tui(controller)
    "created" should {
      val ob1 = new Observable
      "be removed" in {
        controller.remove(tui)
      }
    }
  }

}

