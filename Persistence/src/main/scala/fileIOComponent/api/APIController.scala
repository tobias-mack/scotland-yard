package fileIOComponent.api

import com.google.inject.{Guice, Inject, Injector}
import fileIOComponent.{FileIOInterface, PersistenceModule, FileIOInterface }
import fileIOComponent.databaseComponent.{DBInterface,DAOInterface}
import java.io.*
import play.api.libs.json.{JsValue, Json}

import scala.io.Source

object APIController :

  val injector: Injector = Guice.createInjector(PersistenceModule)
  val fileIO = injector.getInstance(classOf[FileIOInterface])
  val database = injector.getInstance(classOf[DBInterface])
  val databaseDAO = injector.getInstance(classOf[DAOInterface])


  def load(): String =
    val file = scala.io.Source.fromFile("board.json")
    try file.mkString finally file.close()


  def save(gameAsJson: String): Unit =
    val pw = new PrintWriter(new File("." + File.separator + "game.json"))
    pw.write(gameAsJson)
    pw.close



