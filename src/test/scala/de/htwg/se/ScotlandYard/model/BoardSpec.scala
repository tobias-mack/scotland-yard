
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
      "should be able to move Detective to 3" in {
        board1.movePlayer(board1, 3,1,1)should be(board1.copy(cell = Vector[Cell](Cell(15))))
      }
      "should be able to move MisterX to 2" in {
        board1.movePlayer(board1, 2, 0,1) should be(board1.copy(cell = Vector[Cell](Cell(15))))


      }
    }
  }
}

