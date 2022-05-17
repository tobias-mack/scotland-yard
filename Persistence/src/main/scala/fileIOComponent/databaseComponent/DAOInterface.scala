package fileIOComponent.databaseComponent

import model.gameComponents.{Cell,Ticket}
import scala.concurrent.Future

trait DAOInterface {
  def create: Unit

  def read(playerNumber: Int):String

  def update(input: String): Unit

  def delete: Future[Any]
}
