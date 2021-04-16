
package de.htwg.se.ScotlandYard.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class BoardSpec extends AnyWordSpec with Matchers {
  "A Board" when {
    "when created" should {
      val board1 = new Board
        "have 3 cells" in {
          board1.getCells should be (1,2,3)
        }
        "have connections to each other" in {
          board1.cell1.connection should be (List(2))
          board1.cell2.connection should be (List(3))
          board1.cell3.connection should be (List(1))
        }
        "be a taxi/bus/sub" in {
          board1.cell1.isTaxi should be (true)
          board1.cell2.isBus should be (true)
          board1.cell3.isSub should be (true)
        }
    }
  }

}

