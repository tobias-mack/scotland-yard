package de.htwg.se.ScotlandYard.model
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class DetectiveSpec extends AnyWordSpec with Matchers {
  "Detective" when {
    val dec1 = Detective(null)
    "created" should {
      "be \"empty\":" in {
        dec1.name should be (null)
        dec1.cell should be (Cell())
        dec1.ticket should be (Ticket())
        dec1.typ should be (0)
      }
    }
    "with changed parameters" should {
      val dec2 = Detective("name")
      "change his name" in {
        dec2.setName(dec1, "jojo") should be(Detective(name = "jojo"))
      }
      "change his Cell" in {
        dec2.setCell(dec2,Cell(2)) should be (Detective("name",cell = Cell(2)))
      }
      "change his Tickets" in {
        dec2.setTicket(dec2,Ticket(3,3,3,3)) should be (Detective("name",Cell(),ticket = Ticket(3,3,3,3)))
      }
    }
  }
}