
package de.htwg.se.ScotlandYard.model

import de.htwg.se.ScotlandYard.model.gameComponents.{Board, BoardHardStrategy, Cell, Detective, MisterX, Player, Ticket}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class BoardHardStrategySpec extends AnyWordSpec with Matchers {
    "A Board on Hard Mode" when {
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
          board3.player should be (Vector(gameComponents.MisterX("jo",Cell(5),Ticket(9,5,3,5)), Detective("xo",Cell(1),Ticket(10,8,4))))
        }
      }
      "should be able to move around" should {
        val board4 = (new BoardHardStrategy).movePlayer(board3,15,0,1)
        val board5 = (new BoardHardStrategy).movePlayer(board4,3,1,2)
        val board6 = (new BoardHardStrategy).movePlayer(board5,12,0,3)
        val board7 = (new BoardHardStrategy).movePlayer(board6,11,1,4)
        "in " in {
          board4.player(0) should be (gameComponents.MisterX("jo",Cell(15),Ticket(7,5,3,5)))
          board5.player(1) should be (gameComponents.Detective("xo", Cell(3), Ticket(10,6,4)))
          board6.player(0) should be (gameComponents.MisterX("jo",Cell(12),Ticket(7,5,1,5)))
          board7.player(1) should be (gameComponents.Detective("xo",Cell(11),Ticket(10,6,4,-2)))
        }
        " and also print the output" in {
          board5.toString should be ("  \u001b[30m" + "jo" + " \u001b[0mis at \u001b[34mposition " + "15" + " \u001b[0mand has\u001b[33m " +
            "7 Taxi \u001b[0mtickets,\u001b[32m" + "5 Bus \u001b[0mtickets, \u001b[31m" +
            "3 Subway \u001b[0mtickets" + "; \n" +
            "  \u001b[30m" + "xo" + " \u001b[0mis at \u001b[34mposition " + "3" + " \u001b[0mand has\u001b[33m " +
            "10 Taxi \u001b[0mtickets,\u001b[32m" + "6 Bus \u001b[0mtickets, \u001b[31m" +
            "4 Subway \u001b[0mtickets" + "; \n" +
            "  Board")
          board7.toString should be ("  \u001b[30m" + "jo" + " \u001b[0mis at \u001b[34mposition " + "12" + " \u001b[0mand has\u001b[33m " +
            "7 Taxi \u001b[0mtickets,\u001b[32m" + "5 Bus \u001b[0mtickets, \u001b[31m" +
            "1 Subway \u001b[0mtickets" + "; \n" +
            "  \u001b[30m" + "xo" + " \u001b[0mis at \u001b[34mposition " + "11" + " \u001b[0mand has\u001b[33m " +
            "10 Taxi \u001b[0mtickets,\u001b[32m" + "6 Bus \u001b[0mtickets, \u001b[31m" +
            "4 Subway \u001b[0mtickets" + "; \n" +
            "  Board")
        }
      }


    }
}

