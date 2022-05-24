
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

  private val httpProtocol = http
    .baseUrl("http://localhost:8081")
    .inferHtmlResources(AllowList(), DenyList())
    .acceptHeader("*/*")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("PostmanRuntime/7.29.0")
  
  private val headers_0 = Map("Postman-Token" -> "a3e1ffee-01ab-4e8a-bc04-88921a13cd94")
  
  private val headers_1 = Map(
  		"Content-Type" -> "text/plain",
  		"Postman-Token" -> "6b7232ab-bd4b-4938-bf3e-52b217f6ff7d"
  )
  
  private val headers_2 = Map(
  		"Content-Type" -> "text/plain",
  		"Postman-Token" -> "fb93d0ac-c873-479d-98af-ff4599942483"
  )
  
  private val headers_3 = Map(
  		"Content-Type" -> "text/plain",
  		"Postman-Token" -> "ad7fa284-d50e-46f3-8cd9-1a86a0e688db"
  )
  
  private val headers_4 = Map(
  		"Content-Type" -> "text/plain",
  		"Postman-Token" -> "183b22a3-8bb5-42d8-8b18-b33d2a01d034"
  )


  private val scn = scenario("RecordedSimulation")
    .exec(
      http("request_0")
        .get("/db/read/1")
        .headers(headers_0)
    )
    .pause(3)
    .exec(
      http("request_1")
        .post("/db/update")
        .headers(headers_1)
        .body(RawFileBody("recordedsimulation/0001_request.txt"))
    )
    .pause(5)
    .exec(
      http("request_2")
        .post("/db/update")
        .headers(headers_2)
        .body(RawFileBody("recordedsimulation/0002_request.txt"))
    )
    .pause(26)
    .exec(
      http("request_3")
        .post("/db/update")
        .headers(headers_3)
        .body(RawFileBody("recordedsimulation/0003_request.txt"))
    )
    .pause(8)
    .exec(
      http("request_4")
        .post("/db/createGame")
        .headers(headers_4)
        .body(RawFileBody("recordedsimulation/0004_request.txt"))
    )

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
