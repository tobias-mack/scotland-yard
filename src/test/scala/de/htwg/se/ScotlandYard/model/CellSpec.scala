
package de.htwg.se.ScotlandYard.model

import de.htwg.se.ScotlandYard.model.gameComponents.Cell
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class CellSpec extends AnyWordSpec with Matchers :
  "A Cell" when {
    "created" should {
      val cell1 = Cell()
      "be set to the staring parameters" in {
        cell1.number should be(0)
      }
    }
  }

