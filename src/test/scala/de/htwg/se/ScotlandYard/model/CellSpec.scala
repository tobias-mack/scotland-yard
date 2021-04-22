package de.htwg.se.ScotlandYard.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class CellSpec extends AnyWordSpec with Matchers {
  "A Cell" when {
    "not set to any value" should {
      val emptyCell = Cell(0,0,Nil,0)
      "have value 0" in {
        emptyCell.value == 0 && emptyCell.name == 0 && emptyCell.connection == Nil
      }
      "not be set to anything" in {
        emptyCell.isSub should be (false)
        emptyCell.isTaxi should be (false)
        emptyCell.isBus should be (false)
        emptyCell.name should be (0)
        emptyCell.getConnections should be (Nil)
      }
    }
    "set to taxi and given a name" should {
      val taxiCell = Cell(1,13,List(1,2),0)
      "return its status" in {
        taxiCell.value should be (1)
        taxiCell.connection should be (List(1,2))
      }
      "have value 1 and have a name and have a connection" in {
        taxiCell.value should be(1)
        taxiCell.name should be (13)
        taxiCell.connection should be (List(1,2))
      }
      "return something on getConnection" in {
        taxiCell.getConnections should be (List(1,2))
      }
      "be set to taxi" in {
        taxiCell.isTaxi should be (true)
      }
      "set to any name" should {
        val nameCell = Cell(1,123,Nil,0)
        "return its name" in {
          nameCell.getName should be (123)
        }
      }
      "checking vacant/ occupied" should {
        val newCell = Cell(1,2,List(1,2),1)
        "vacate" in {
          newCell.vacant should be (Cell(1,2,List(1,2),0))
        }
        "occupie" in {
          newCell.occupie should be (Cell(1,2,List(1,2),1))
        }
      }
    }
  }
}
