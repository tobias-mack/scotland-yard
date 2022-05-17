package fileIOComponent.api

import com.google.inject.{Guice, Inject, Injector}
import fileIOComponent.{FileIOInterface, PersistenceModule}
import fileIOComponent.databaseComponent.{DAOInterface, DBInterface}
import model.gameComponents.Player

import java.io.*
import play.api.libs.json.{JsValue, Json}

import scala.io.Source

object APIController:

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

	// DBInterface methods

	def createDB() =
		database.createDB()

	def getPlayer(input: String): Option[(Int, String, Int, Int, Int, Int, Int, Int)] =
		database.readPlayer(input.toInt)

	def updatePlayer(playerId: Int, position: Int): String =
		database.updatePlayer(playerId, position)

	def deletePlayer(playerId: Int): String =
		database.deletePlayer(playerId)

	def createPlayer(playerId: Int, player: Player): Int =
		database.createPlayer(playerId, player)


	// DAOInterface methods

	def createDAO(): Unit =
		databaseDAO.create()

	def readDAO(): String =
		databaseDAO.read()

	def updateDAO(input: String) =
		databaseDAO.update(input)

	def deleteDAO(): Unit =
		databaseDAO.delete()

