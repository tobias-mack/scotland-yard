package de.htwg.se.ScotlandYard.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

 class TicketSpec extends AnyWordSpec with Matchers {
  "A Ticket" when {
    "created" should {
      val ticket1 = Ticket()
      "have the starting values" in {
        ticket1.taxi should be (0)
        ticket1.bus should be (0)
        ticket1.subway should be (0)
        ticket1.black should be (0)
      }
    }
}
}
