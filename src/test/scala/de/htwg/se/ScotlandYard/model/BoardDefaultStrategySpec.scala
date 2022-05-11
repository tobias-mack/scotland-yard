/*package de.htwg.se.ScotlandYard.model

import de.htwg.se.ScotlandYard.model.gameComponents.{Board, Cell, Ticket}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.collection.mutable.ListBuffer

class BoardDefaultStrategySpec extends AnyWordSpec with Matchers :
	"A Default Board" when {
		val travelLog = ListBuffer[Int]()
		val board1 = Board()
		val board2 = Board().addDetective(Board(), "jo", Cell(5), Ticket(9, 5, 3, 5))
		val board3 = board2.addDetective(board2, "xo", Cell(1), Ticket(10, 8, 4))
		"created" should {
			"should have starting parameters" in {
				board1.player should be(Vector[Player]())
			}
		}
		"Players are added" should {
			"in " in {
				board2.player should be(Vector(MisterX("jo", Cell(5), Ticket(9, 5, 3, 5))))
				board3.player should be(Vector(MisterX("jo", Cell(5), Ticket(9, 5, 3, 5)), Detective("xo", Cell(1), Ticket(10, 8, 4))))
			}
		}
		"should be able to move around" should {
			val board4 = (new BoardDefaultStrategy).movePlayer(board3, 15, 0, 1, travelLog, 3)
			val board5 = (new BoardDefaultStrategy).movePlayer(board4, 3, 1, 2, travelLog, 3)
			val board6 = (new BoardDefaultStrategy).movePlayer(board5, 12, 0, 3, travelLog, 3)
			val board7 = (new BoardDefaultStrategy).movePlayer(board6, 11, 1, 4, travelLog, 3)
			"in " in {
				board4.player(0) should be(MisterX("jo", Cell(15), Ticket(8, 5, 3, 5)))
				board5.player(1) should be(Detective("xo", Cell(3), Ticket(10, 7, 4)))
				//board5.player(0) should be (MisterX("jo",Cell(15),Ticket(8,5,3,5)))
				board6.player(0) should be(MisterX("jo", Cell(12), Ticket(8, 5, 2, 5)))
				board7.player(1) should be(Detective("xo", Cell(11), Ticket(10, 7, 4, -1)))
			}
			" and also print the output" in {
				board5.toString should be("  BOARD: " +
					"\n \u001b[33m" + "Taxi-Locations: " +
					"1 2 3 4 5 6 7 8 9 10 12 13 14 15 16 18 19 20 " +
					"\n \u001b[32m" + "Bus-Locations: " +
					"1 2 4 5 6 7 8 9 10 11 12 14 15 16 17 18 20 21 " +
					"\n \u001b[31m" + "Subway-Locations: " +
					"7 9 14 15 " +
					"\n  \u001b[30m" + "jo" + " \u001b[0mis at \u001b[34mposition " + "15" + " \u001b[0mand has\u001b[33m " +
					"8 Taxi \u001b[0mtickets,\u001b[32m" + "5 Bus \u001b[0mtickets, \u001b[31m" +
					"3 Subway \u001b[0mtickets" + "; \n" +
					"  \u001b[30m" + "xo" + " \u001b[0mis at \u001b[34mposition " + "3" + " \u001b[0mand has\u001b[33m " +
					"10 Taxi \u001b[0mtickets,\u001b[32m" + "7 Bus \u001b[0mtickets, \u001b[31m" +
					"4 Subway \u001b[0mtickets" + "; \n")
				board7.toString should be("  BOARD: " +
					"\n \u001b[33m" + "Taxi-Locations: " +
					"1 2 3 4 5 6 7 8 9 10 12 13 14 15 16 18 19 20 " +
					"\n \u001b[32m" + "Bus-Locations: " +
					"1 2 4 5 6 7 8 9 10 11 12 14 15 16 17 18 20 21 " +
					"\n \u001b[31m" + "Subway-Locations: " +
					"7 9 14 15 " +
					"\n  \u001b[30m" + "jo" + " \u001b[0mis at \u001b[34mposition " + "12" + " \u001b[0mand has\u001b[33m " +
					"8 Taxi \u001b[0mtickets,\u001b[32m" + "5 Bus \u001b[0mtickets, \u001b[31m" +
					"2 Subway \u001b[0mtickets" + "; \n" +
					"  \u001b[30m" + "xo" + " \u001b[0mis at \u001b[34mposition " + "11" + " \u001b[0mand has\u001b[33m " +
					"10 Taxi \u001b[0mtickets,\u001b[32m" + "7 Bus \u001b[0mtickets, \u001b[31m" +
					"4 Subway \u001b[0mtickets" + "; \n")
			}
		}


	}
*/