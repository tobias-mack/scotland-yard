package de.htwg.se.ScotlandYard.model.fileIOComponent

import de.htwg.se.ScotlandYard.controller.ControllerInterface
import de.htwg.se.ScotlandYard.model.gameComponents.Player


trait FileIOInterface {

  def load(controller: ControllerInterface): Vector[Player]
  def save(controller: ControllerInterface): Unit
}
