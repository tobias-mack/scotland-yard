
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
        board1.cell should be(Vector[Cell]())
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
      val board4 = board3.movePlayer(board3,15,0)
      val board5 = board4.movePlayer(board4,3,1)
      "in " in {
        board4.player(0) should be (MisterX("jo",Cell(15),Ticket(8,5,3,5)))
        board5.player(1) should be (Detective("xo", Cell(3), Ticket(9,8,4)))
        board5.player(0) should be (MisterX("jo",Cell(15),Ticket(8,5,3,5)))
      }
      " and also print the output" in {
        board5.toString should be (
          """jo is at position 15 and has 8 Taxi tickets,5 Bus tickets, 3 Subway tickets
            |xo is at position 3 and has 9 Taxi tickets,8 Bus tickets, 4 Subway tickets
            |""".stripMargin)
      }
    }


  }
}

