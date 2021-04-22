
package de.htwg.se.ScotlandYard.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class BoardSpec extends AnyWordSpec with Matchers {
  "A Board" when {
    "when created" should {
      val c = Cell(1,1,Nil,0)
      val cc = Cell(1,2,Nil,0)
      val ccc = Cell(1,3,Nil,1)
      val board1 = Board(c,cc,ccc)
      "retun its cells" in {
        board1.getCell1 should be (c)
        board1.getCell2 should be (cc)
        board1.getCell3 should be (ccc)

      }

        }
    }

}

