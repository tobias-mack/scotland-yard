

package de.htwg.se.ScotlandYard.model

import de.htwg.se.ScotlandYard.model.gameComponents.{Board, Cell, Detective, MisterX, Player, Ticket}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class BoardSpec extends AnyWordSpec with Matchers {
  "A Board" when {
    val board1 = Board()
    val board2 = Board().addDetective(Board(),"jo")
    val board3 = board2.addDetective(board2,"xo")
    "created" should {
      "should have starting parameters" in {
        board1.player should be(Vector[Player]())
      }
    }
    "Players are added" should {
      "in " in {
        board2.player should be (Vector(MisterX("jo",Cell(5),Ticket(9,5,3,5))))
        board3.player should be (Vector(gameComponents.MisterX("jo",Cell(5,List(),List(),List()),Ticket(9,5,3,5)),
          Detective("xo",Cell(1),Ticket(10,8,4))))
      }
      "and print the output" in {
        board3.toString should be ("  \u001b[30m" + "jo" + " \u001b[0mis at \u001b[34mposition " + "5" + " \u001b[0mand has\u001b[33m " +
          "9 Taxi \u001b[0mtickets,\u001b[32m" + "5 Bus \u001b[0mtickets, \u001b[31m" +
          "3 Subway \u001b[0mtickets" + "; \n" +
          "  \u001b[30m" + "xo" + " \u001b[0mis at \u001b[34mposition " + "1" + " \u001b[0mand has\u001b[33m " +
          "10 Taxi \u001b[0mtickets,\u001b[32m" + "8 Bus \u001b[0mtickets, \u001b[31m" +
          "4 Subway \u001b[0mtickets" + "; \n" +
          "  Board")
      }
      "and be able to move to neighbour cell" in {
        board3.checkPossDest(6,1,0) should be (true)
      }
      "and not be in LoosingState" in {
        board3.checkLoosing() should be (false)
      }
    }
  }
}


