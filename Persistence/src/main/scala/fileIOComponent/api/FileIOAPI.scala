package fileIOComponent.api
import fileIOComponent.api.APIController
import akka.http.scaladsl.server.Directives.{complete, concat, get, path}
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives.*
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCode}
import akka.http.scaladsl.server.{ExceptionHandler, Route}

import scala.concurrent.ExecutionContextExecutor
import scala.util.{Failure, Success}

object FileIOAPI {

  val routes: String =
    "Persistance API ---- Routes: /fileIO/load ---- /fileIO/save"

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
    }
  )

  val bindingFuture = Http().newServerAt("0.0.0.0", 8081).bind(route)

  bindingFuture.onComplete{
    case Success(binding) => {
      val address = binding.localAddress
      println(s"File IO REST service online at http://localhost:${address.getPort}\nPress RETURN to stop...")
    }
    case Failure(exception) => {
      println("File IO REST service couldn't be started! Error: " + exception + "\n")
    }
  }

}
