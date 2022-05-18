package fileIOComponent.databaseComponent.Slick

import fileIOComponent.databaseComponent.DAOInterface
import fileIOComponent.databaseComponent.Slick.Tables.{GameTable, PlayerTable}
import com.google.inject.Inject
import play.api.libs.json.{JsArray, JsValue, Json}
import slick.jdbc.JdbcBackend.Database
import slick.lifted.TableQuery
import slick.jdbc.PostgresProfile.api.*
import model.gameComponents.Board

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.{Duration, DurationInt}
import scala.concurrent.{Await, Future}
import scala.io.StdIn
import scala.util.{Failure, Success, Try}


class DAOSlickImpl @Inject() extends DAOInterface :

	val connectIP = sys.env.getOrElse("POSTGRES_IP", "localhost").toString
	val connectPort = sys.env.getOrElse("POSTGRES_PORT", 5432).toString.toInt
	val database_user = sys.env.getOrElse("POSTGRES_USER", "postgres").toString
	val database_pw = sys.env.getOrElse("POSTGRES_PASSWORD", "postgres").toString
	val database_name = sys.env.getOrElse("POSTGRES_DB", "postgres").toString

	val database =
		Database.forURL(
			url = s"jdbc:postgresql://$connectIP:$connectPort/${database_name}?serverTimezone=UTC",
			user = database_user,
			password = database_pw,
			driver = "org.postgresql.Driver")

	val playerTable = TableQuery[PlayerTable]
	val gameTable = TableQuery[GameTable]

	override def create: Unit =
		val gameDB = Future(Await.result(database.run(
			DBIO.seq(
				playerTable.schema.createIfNotExists,
				gameTable.schema.createIfNotExists
			)), Duration.Inf))
		gameDB.onComplete {
			case Success(_) =>
				print("Connection to DB & Creation of Tables successful!")
				initializeDatabase()
			case Failure(e) => print("\nError: " + e + "\n")
		}

	def initializeDatabase() =
		val initializer = Future(Await.result(database.run(
			DBIO.seq(
				gameTable += (0, "", 2, 1),
				playerTable ++= Seq(
					(0, "mrX", 2, 5, 5, 5, 5, 1),
					(1, "detective", 20, 5, 5, 5, 5, 0)
				)
			)), Duration.Inf))
		initializer.onComplete {
			case Success(_) =>
				print("\ninitialized Database!")
			case Failure(e) => print("\nError: " + e + "\n")
		}
		//Await.result(database.run(gameTable += (0, "2", 3, 0)), atMost = 10.second)
		//Await.result(database.run(playerTable += (0, "mrX", 2, 5, 5, 5, 5, 1)), atMost = 10.second)


	override def read: String =
		val query = sql"""SELECT * FROM PLAYER""".as[(Int, String, Int, Int, Int, Int, Int, Int)] // not sure
		val result = Await.result(database.run(query), atMost = 10.second)
		result.toString()


	override def update(input: String): Unit =
		val gameJson: JsValue = Json.parse(input)
		val currentPlayer = (gameJson \ "gameInformation" \ "currentPlayer").get.toString().toInt
		val travelLog = (gameJson \ "gameInformation" \ "travelLog").get.toString()
		val revealCounter = (gameJson \ "gameInformation" \ "revealCounter").get.toString().toInt
		val playerNumber = (gameJson \ "gameInformation" \ "playerNumber").get.toString().toInt


	//weird
	override def delete: Unit =
		val action = playerTable.delete
		Await.result(database.run(action), atMost = 10.second)
		val deleteQuery = sql"""ALTER SEQUENCE "PLAYER_id_seq" RESTART WITH 1""".as[Int]
		Await.result(database.run(deleteQuery), atMost = 10.second)
