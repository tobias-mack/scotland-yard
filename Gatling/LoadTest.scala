
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class LoadTest extends Simulation {

  private val httpProtocol = http
    .baseUrl("http://localhost:8081")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader(
      "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0");


  private val scn = scenario("LoadTest")
    .exec(
      http("creating Game")
        .post("/db/createGame")
        .body(StringBody("""{\n  \"gameInformation\" : {\n    \"currentPlayer\" : 2,\n    \"travelLog\" : \"1 2\",\n    \"revealCounter\" : 1,\n    \"playerNumber\" : 2,\n    \"gameId\": 1\n  },\n  \"players\" : [ {\n    \"name\" : \"Player1\",\n    \"cell\" : 13,\n    \"typ\" : 1,\n    \"ticket\" : {\n      \"taxi\" : 8,\n      \"bus\" : 4,\n      \"subway\" : 3,\n      \"black\" : 5\n    }\n  }, {\n    \"name\" : \"Player2\",\n    \"cell\" : 14,\n    \"typ\" : 0,\n    \"ticket\" : {\n      \"taxi\" : 8,\n      \"bus\" : 8,\n      \"subway\" : 4,\n      \"black\" : 0\n    }\n  } ]\n}"""))
    )
    .pause(1)
    .exec(
      http("update player1")
        .post("/db/update")
        .body(StringBody("""{\n  \"gameInformation\" : {\n    \"currentPlayer\" : 2,\n    \"travelLog\" : \"1 2\",\n    \"revealCounter\" : 1,\n    \"playerNumber\" : 2,\n    \"gameId\": 1\n  },\n  \"players\" : [ {\n    \"name\" : \"Player1\",\n    \"cell\" : 14,\n    \"typ\" : 1,\n    \"ticket\" : {\n      \"taxi\" : 7,\n      \"bus\" : 4,\n      \"subway\" : 3,\n      \"black\" : 5\n    }\n  }, {\n    \"name\" : \"Player2\",\n    \"cell\" : 14,\n    \"typ\" : 0,\n    \"ticket\" : {\n      \"taxi\" : 8,\n      \"bus\" : 8,\n      \"subway\" : 4,\n      \"black\" : 0\n    }\n  } ]\n}"""))
    )
    .pause(1)
    .exec(
      http("update player2")
        .post("/db/update")
        .body(StringBody("""{\n  \"gameInformation\" : {\n    \"currentPlayer\" : 2,\n    \"travelLog\" : \"1 2\",\n    \"revealCounter\" : 1,\n    \"playerNumber\" : 2,\n    \"gameId\": 1\n  },\n  \"players\" : [ {\n    \"name\" : \"Player1\",\n    \"cell\" : 14,\n    \"typ\" : 1,\n    \"ticket\" : {\n      \"taxi\" : 7,\n      \"bus\" : 4,\n      \"subway\" : 3,\n      \"black\" : 5\n    }\n  }, {\n    \"name\" : \"Player2\",\n    \"cell\" : 99,\n    \"typ\" : 0,\n    \"ticket\" : {\n      \"taxi\" : 8,\n      \"bus\" : 8,\n      \"subway\" : 3,\n      \"black\" : 0\n    }\n  } ]\n}"""))
    )

	setUp(scn.inject(atOnceUsers(100))).protocols(httpProtocol)
}
