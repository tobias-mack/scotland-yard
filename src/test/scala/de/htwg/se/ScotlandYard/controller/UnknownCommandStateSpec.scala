package de.htwg.se.ScotlandYard.controller
import de.htwg.se.ScotlandYard.model.Board
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class UnknownCommandStateSpec extends AnyWordSpec with Matchers {
  "A UnknownCommandState" when {
    "created" should {
      val board1 = Board()
      var controller1 = new Controller(board1)
      val gameState1 = GameState(controller1)
      val unknownCommandState = UnknownCommandState(controller1)
      "in" in {
        unknownCommandState.handle("asdf", gameState1) should be (gameState1.nextState(NextPlayerState(controller1)))
      }
    }
  }
}

