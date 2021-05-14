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
        dec1.ticket should be (Ticket(10,8,4))
        dec1.typ should be (0)
      }
    }
    "with changed parameters" should {
      val dec2 = Detective("name")
      "change his name" in {
        dec2.setName(dec1, "jojo") should be(Detective(name = "jojo"))
      }
      "change his Cell" in {
        dec2.setCell(dec2,Cell(2),Ticket(dec2.ticket.taxi,dec2.ticket.bus,dec2.ticket.subway,dec2.ticket.black)) should be (Detective("name",cell = Cell(2)))
      }
    }
  }
}