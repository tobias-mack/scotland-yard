package fileIOComponent

import modell.gameComponents.{Board, Player}
import modell.BoardInterface

trait FileIOInterface:

	def load(): Board

	def save(board: BoardInterface): Unit