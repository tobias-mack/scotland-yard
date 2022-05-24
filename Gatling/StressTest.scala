
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
      http("request_0")
        .get("/db/read/1")
    )
    .pause(3)
    .exec(
      http("request_0")
        .get("/db/read/2")
    )
    .pause(3)
    .exec(
      http("request_0")
        .get("/db/read/1")
    )

	setUp(scn.inject(rampUsersPerSec(3).to(1000).during(2.minutes))).protocols(httpProtocol)
}
