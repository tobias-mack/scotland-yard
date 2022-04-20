package controller.controllerBaseImpl

trait State[T]:
	def handle(string: String, state: GameState): Unit
