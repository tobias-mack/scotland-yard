package fileIOComponent.api

import com.google.inject.{Guice, Inject, Injector}
import fileIOComponent.databaseComponent.DBInterface
import fileIOComponent.{FileIOInterface, PersistenceModule}
import modell.BoardInterface
import modell.gameComponents.Player
import play.api.libs.json.{JsValue, Json}
import slick.ast.StructNode

import java.io.*
import scala.concurrent.Future
import scala.io.Source

object APIController:

  val injector: Injector = Guice.createInjector(PersistenceModule())
  val fileIO: FileIOInterface = injector.getInstance(classOf[FileIOInterface])
  val database: DBInterface = injector.getInstance(classOf[DBInterface])


  def load(): String =
    val file = scala.io.Source.fromFile("board.json")
    try file.mkString finally file.close()


  def save(gameAsJson: String): Unit =
    val pw = new PrintWriter(new File("." + File.separator + "game.json"))
    pw.write(gameAsJson)
    pw.close

  // DBInterface methods

  def createDB(): Unit =
    database.createDB()

  def createGame(gameAsJson: String): Int =
    database.createGame(gameAsJson)

  def read(gameId: Int): String = {
    val game = database.read(gameId)
    game match
      case Some(value) => value
      case None => s"no game found with gameID: $gameId"
  }

  def update(gameAsJson: String): String =
    database.update(gameAsJson)

  def delete(gameId: Int): Future[Any] =
    database.delete(gameId)



