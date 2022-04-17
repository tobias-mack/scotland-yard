package fileIOComponent


trait FileIOInterface:

  def load(controller: ControllerInterface): Vector[Player]

  def save(controller: ControllerInterface): Unit