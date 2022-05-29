
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class StressTest extends Simulation {

  private val httpProtocol = http
    .baseUrl("http://localhost:8081")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader(
      "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0");


  private val scn = scenario("StressTest")
    .exec(
      http("create Game")
        .post("/db/createGame")
        .body(RawFileBody("createGame.json"))
    )
    .pause(1)
    .exec(
      http("updating player 1")
        .post("/db/update")
        .body(RawFileBody("updatePlayer1.json"))
    )
    .pause(1)
    .exec(
      http("updating player 2")
        .post("/db/update")
        .body(RawFileBody("updatePlayer2.json"))
    )

  setUp(scn.inject(rampUsersPerSec(2).to(500).during(2.minutes))).protocols(httpProtocol)
}
