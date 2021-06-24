package de.htwg.se.ScotlandYard.model.fileIOComponent

import de.htwg.se.ScotlandYard.model.BoardInterface


trait FileIOInterface {

  def load: BoardInterface
  def save(Board: BoardInterface): Unit
}
