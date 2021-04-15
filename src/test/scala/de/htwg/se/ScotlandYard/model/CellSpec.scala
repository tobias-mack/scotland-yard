package de.htwg.se.ScotlandYard.model

import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatest.wordspec.AnyWordSpec


class CellSpec extends AnyWordSpec with Matchers {
  "A Cell" when {
    "not set to any value" should {
      val emptyCell = Cell(0,0,0)
      "have value 0" in {
        emptyCell.value == 0 && emptyCell.name == 0 && emptyCell.connection == 0
      }
      "not be set to anything" in {
        emptyCell.isSub should be (false)
        emptyCell.isTaxi should be (false)
        emptyCell.isBus should be (false)
      }
    }
    "set to taxi and given a name" should {
      val taxiCell = Cell(1,13,0)
      "return its status" in {
        taxiCell.value should be (1)
      }
      "have value 1 and have a name and have 0 connection " in {
        taxiCell.value should be(1)
        taxiCell.name should be (13)
        taxiCell.connection should be (0)
      }
      "be set to taxi" in {
        taxiCell.isTaxi should be (true)
      }
    }
  }
}
