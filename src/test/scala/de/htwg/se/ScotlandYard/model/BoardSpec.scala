
package de.htwg.se.ScotlandYard.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class BoardSpec extends AnyWordSpec with Matchers {
  "A Board" when {
    "created" should {
      val board1 = Board()
      "should have starting parameters" in {
        board1.cell should be(Vector[Cell]())
        board1.player should be(Vector[Player](MisterX("MisterX"), Detective("d1"), Detective("d2")))
      }
      // TODO tests are wrong here...
      "should be able to move Detective" in {
        board1.moveDetective(board1, 15) should be(board1.copy(cell = Vector[Cell](Cell(15))))
      }
      "should be able to move MisterX" in {
        board1.moveMRX(board1, 15) should be(board1.copy(cell = Vector[Cell](Cell(15))))


      }
    }
  }
}

