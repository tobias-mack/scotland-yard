package fileIOComponent.api

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCode}
import akka.http.scaladsl.server.Directives.*
import akka.http.scaladsl.server.{ExceptionHandler, Route}
import fileIOComponent.api.APIController

import scala.concurrent.ExecutionContextExecutor
import scala.util.{Failure, Success}

object FileIOAPI {

  val routes: String =
    "Persistance API ---- Routes: \n" +
      "/fileIO/load ---- /fileIO/save\n" +
      "/db/createDAO ---- /db/deleteDAO\n" +
      "/db/readDAO ---- /db/updateDAO\n" +
      "/db/getplayer ---- /db/updateplayer\n" +
      "/db/addplayer ---- /db/deleteplayer\n"

  val system: ActorSystem[Any] = ActorSystem(Behaviors.empty, "my-system")

  given ActorSystem[Any] = system

  val executionContext: ExecutionContextExecutor = system.executionContext

  given ExecutionContextExecutor = executionContext

  val route = concat(
    pathSingleSlash {
      complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, routes))
    },
    path("fileIO" / "load") {
      get {
        complete(HttpEntity(ContentTypes.`application/json`, APIController.load()))
      }
    },
    path("fileIO" / "save") {
      concat(
        post {
          entity(as[String]) { board =>
            APIController.save(board)
            complete("saved!")
          }
        }
      )
    },

    // DB methods
    path("db" / "createDB") {
      get {
        APIController.createDB()
        complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "Database created."))
      }
    },
    path("db" / "createGame") {
        post {
          entity(as[String]) { board =>
            complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, APIController.createGame(board).toString))
          }
        }

    },
    path("db" / "read" / Segment) {
      command => {
        complete(HttpEntity(ContentTypes.`application/json`, APIController.read(command.toInt)))
      }
    },
    path("db" / "update") {
        post {
          entity(as[String]) { board =>
            complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, APIController.update(board)))
          }
        }
    },
    path("db" / "delete" / Segment) {
      command => {
        complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, APIController.delete(command.toInt).toString))
      }
    }
  )

  val bindingFuture = Http().newServerAt("0.0.0.0", 8081).bind(route)

  //APIController.createDB()

  bindingFuture.onComplete {
    case Success(binding) => {
      val address = binding.localAddress
      println(s"File IO REST service online at http://localhost:${address.getPort}\nPress RETURN to stop...")
    }
    case Failure(exception) => {
      println("File IO REST service couldn't be started! Error: " + exception + "\n")
    }
  }

}
