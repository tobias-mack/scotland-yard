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

	path("db" / "getplayer" / Segment) {
		command => {
			complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, APIController.getPlayer(command).toString))
		}
	},
		path("db" / "getplayer") {
			get {
				complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, APIController.getAllPlayer().toString()))
			}
		},
		path("db" / "getgames") {
			get {
				complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, APIController.getAllGames().toString()))
			}
		},
	path("db" / "updateplayer" / Segments) {
		command => {
			complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, APIController.updatePlayer(command.head.toInt, command(1).toInt).toString))
		}
	},
	path("db" / "deleteplayer" / Segment) {
		command => {
			complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, APIController.deletePlayer(command.toInt).toString))
		}
	},
	path("db" / "addplayer" / "1" / Segment) {
		command => {
			complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, APIController.createPlayer(1, command).toString))
		}
	},

		// DAO methods - CRUD - change content type to json if needed

		path("db" / "createDAO") {
			get {
				APIController.createDAO()
				complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "Database created"))
			}
		},
		path("db" / "readDAO") {
			get {
				complete(HttpEntity(ContentTypes.`application/json`, APIController.readDAO()))
			}
		},
		path("db" / "updateDAO") {
			concat(
				post {
					entity(as[String]) { game =>
						APIController.updateDAO(game)
						complete("Game saved to DB")
					}
				}
			)
		},
		path("db" / "deleteDAO") {
			get {
				APIController.deleteDAO()
				complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "Database cleared."))
			}
		},
	)

	val bindingFuture = Http().newServerAt("0.0.0.0", 8081).bind(route)

	APIController.createDAO()

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
