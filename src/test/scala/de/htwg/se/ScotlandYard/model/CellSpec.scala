package de.htwg.se.ScotlandYard.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class CellSpec extends AnyWordSpec with Matchers {
  "A Cell" when {
    "created" should {
      val cell1 = Cell()
      "be set to the staring parameters" in {
        cell1.typ should be (1)
        cell1.number should be (0)
        cell1.nearbyTaxi should be (List())
        cell1.nearbyBus should be (List())
        cell1.nearbySub should be (List())
      }
    }
    //test toString here when we know how it sholud look like
  }
}
