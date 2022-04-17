
package util

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class ObserverSpec extends AnyWordSpec with Matchers {
  "An Observer" when {
    val controller = new Controller()
    val tui = Tui(controller)
    "created" should {
      "be removed" in {
        controller.remove(tui)
      }
    }
  }

}


