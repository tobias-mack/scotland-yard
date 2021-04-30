
package de.htwg.se.ScotlandYard.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class BoardSpec extends AnyWordSpec with Matchers {
  "A Board" when {
    val board1 = Board()
    val board2 = Board().addDetective(Board(),"jo")
    val board3 = board2.addDetective(board2,"xo")
    "created" should {
      "should have starting parameters" in {
        board1.cell should be(Vector[Cell](Cell(1,List(),List(),List()), Cell(2,List(),List(),List()), Cell(3,List(),List(),List())))
        board1.player should be(Vector[Player]())
      }
    }
    "Players are added" should {
      "in " in {
        board2.player should be (Vector(MisterX("jo",Cell(),Ticket(9,5,3,5))))
        board3.player should be (Vector(MisterX("jo",Cell(0,List(),List(),List()),Ticket(9,5,3,5)), Detective("xo",Cell(),Ticket(10,8,4))))
      }
    }
    "should be able to move around" should {
      val board4 = board3.movePlayer(board3,15,0,1)
      val board5 = board4.movePlayer(board4,3,1,1)
      "in " in {
        board4.player(0) should be (MisterX("jo",Cell(15),Ticket(8,5,3,5)))
        board5.player(1) should be (Detective("xo", Cell(3), Ticket(9,8,4)))
        board5.player(0) should be (MisterX("jo",Cell(15),Ticket(8,5,3,5)))
      }
      " and also print the output" in {
        board5.toString should be ("  \u001b[30m" + "jo" + " \u001b[0mis at \u001b[34mposition " + "15" + " \u001b[0mand has\u001b[33m " +
          "8 Taxi \u001b[0mtickets,\u001b[32m" + "5 Bus \u001b[0mtickets, \u001b[31m" +
          "3 Subway \u001b[0mtickets" + "; \n" +
          "  \u001b[30m" + "xo" + " \u001b[0mis at \u001b[34mposition " + "3" + " \u001b[0mand has\u001b[33m " +
          "9 Taxi \u001b[0mtickets,\u001b[32m" + "8 Bus \u001b[0mtickets, \u001b[31m" +
          "4 Subway \u001b[0mtickets" + "; \n" +
        "  Board: 1 , 2 , 3 ")
      }
    }


  }
}

