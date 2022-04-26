package de.htwg.se.ScotlandYard.controller

object HttpServerRouting {

	val injector: Injector = Guice.createInjector(ScotlandYardModule())
	val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])

	def main(args: Array[String]): Unit = {

		implicit val system = ActorSystem(Behaviors.empty, "my-system")
		implicit val executionContext = system.executionContext
		val servicePort = 8080

		val route =
			concat(
				get {
					path("load") {
						controller.load()
						complete(HttpEntity(ContentTypes.`application/json`, "loaded"))
					}
				},
				post {
					path("save") {
						controller.save()
						complete(HttpEntity(ContentTypes.`application/json`, "saved"))
					}
				}
			)

		val bound = Http().newServerAt("localhost", servicePort).bind(route)

		bound.onComplete {
			case Success(binding) => print(binding)
			case Failure(exception) => print(exception.getMessage)
		}
	}

}
