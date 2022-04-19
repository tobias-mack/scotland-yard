package fileIOComponent

import model.gameComponents.Player
import model.BoardInterface


trait FileIOInterface:

	def load(board: BoardInterface): Vector[Player]

	def save(board: BoardInterface): Unit