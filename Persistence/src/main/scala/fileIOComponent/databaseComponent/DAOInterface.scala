package fileIOComponent.databaseComponent

import model.gameComponents.{Cell,Ticket}
import scala.concurrent.Future

trait DAOInterface {
  def create: Unit

  def read:String

  def update(input: String): Unit

  def delete:Unit
}
