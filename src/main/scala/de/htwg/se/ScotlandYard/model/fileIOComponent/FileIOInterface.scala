package de.htwg.se.ScotlandYard.model.fileIOComponent

import de.htwg.se.ScotlandYard.controller.ControllerInterface


trait FileIOInterface {

  def load: ControllerInterface
  def save(controller: ControllerInterface): Unit
}
