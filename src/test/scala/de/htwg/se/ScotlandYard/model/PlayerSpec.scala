package de.htwg.se.ScotlandYard.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class PlayerSpec extends AnyWordSpec with Matchers {
  "A Player" when {
    "set to a name and status and given tickets" should {
      val cell1 = Cell(1,1,List(2),0)
      val cell2 = Cell(1,2,List(1),0)
      val cell3 = Cell(2,3,List(2),0)
      val ocell = Cell(3,4,List(1),1)
      val player1 = Player("Player1",(11,8,4),cell1)
      "return ticketLeft, getPosition and getName" in {
        player1.getName should be ("Player1")
        player1.ticketLeft() should be ((11,8,4))
        player1.getPosition should be (cell1)
      }
      "moved should reduce according ticket " in {
        player1.moveTo(cell2,2) should be (Player("Player1", (11,7,4), cell2))
        player1.moveTo(cell1,1) should be (Player("Player1", (10,8,4), cell1))
        player1.moveTo(cell3,3) should be (Player("Player1", (11,8,3), cell3))
        // it doesnt work in a chain. should it?
      }
      "should not move on an occupied cell" in {
        player1.moveTo(ocell,1) should be (Player("Player1", (11,8,4), cell1))
      }
      //unapply
    }
  }
}
