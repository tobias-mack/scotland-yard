package tools.util

trait State[T]:
  def handle(string: String, state: GameState): Unit
