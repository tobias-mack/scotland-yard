package de.htwg.se.ScotlandYard.aview.api

import akka.http.scaladsl.server.Directives.{complete, concat, get, path}
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives.*
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse, StatusCode}
import akka.http.scaladsl.server.{ExceptionHandler, Route, StandardRoute}
import de.htwg.se.ScotlandYard.controller.ControllerInterface

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.util.{Failure, Success, Try}

object GameAPI {

  val system: ActorSystem[Any] = ActorSystem(Behaviors.empty, "my-system")
  given ActorSystem[Any] = system
  // needed for the future flatMap/onComplete in the end
  val executionContext: ExecutionContextExecutor = system.executionContext
  given ExecutionContextExecutor = executionContext

  def apply(controller: ControllerInterface) =
    val routes: String =
      "Game API ---- Routes: /game ----"

    val route = concat(
      pathSingleSlash {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, routes))
      },
      path("game") {
        get {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, controller.board.toString))
        }
      },
      path("redo") {
        concat(
          get {
            complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, controller.board.toJsonString))
          },
          post {
            controller.redo()
            complete("redo success")
          }
        )
      },
      path("undo") {
        concat (
          get {
            controller.undo()
            complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, controller.board.toJsonString))
          }
        )
      }
    )

    val bindingFuture = Http().newServerAt("0.0.0.0", 8080).bind(route)
    bindingFuture.onComplete {
      case Success(binding) => {
        val address = binding.localAddress
        println(s"View REST service online at http://localhost:${address.getPort}\nPress RETURN to stop...")
      }
      case Failure(exception) => {
        println(s"""View REST service couldn't be started! Error: $exception""")
      }
    }
}
