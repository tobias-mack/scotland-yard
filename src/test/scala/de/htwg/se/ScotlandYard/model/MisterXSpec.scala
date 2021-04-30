package de.htwg.se.ScotlandYard.model
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MisterXSpec extends AnyWordSpec with Matchers {
  "Mister X" when {
    val mrx1 = MisterX(null)
    "created" should {
      "be \"empty\":" in {
        mrx1.name should be (null)
        mrx1.cell should be (Cell())
        mrx1.ticket should be (Ticket(9,5,3,5))
        mrx1.typ should be (1)
      }
    }
    "with changed parameters" should {
      val mrx2 = MisterX("name")
      "change his name" in {
        mrx2.setName(mrx1, "jojo") should be(MisterX(name = "jojo"))
      }
      "change his Cell" in {
        mrx2.setCell(mrx2,Cell(2)) should be (MisterX("name",cell = Cell(2)))
      }
      "change his Tickets" in {
        mrx2.setTicket(mrx2,Ticket(3,3,3,3)) should be (MisterX("name",Cell(),ticket = Ticket(3,3,3,3)))
      }
    }
  }
}
