package fileIOComponent.databaseComponent.Slick

import com.google.inject.Inject
import fileIOComponent.databaseComponent.{DBInterface, GameData, JsonHelper}
import fileIOComponent.databaseComponent.Slick.Tables.{GameTable, PlayerTable}
import play.api.libs.json.{JsArray, JsValue, Json}

import scala.language.postfixOps
//import slick.driver.PostgresDriver.api.*
import slick.jdbc.JdbcBackend.Database
import slick.lifted.TableQuery
import slick.jdbc.PostgresProfile.api.*

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.{Duration, DurationInt}
import scala.concurrent.{Await, Future}
import scala.io.StdIn
import scala.util.{Failure, Success, Try}


class DBSlickImpl @Inject() extends DBInterface:

  val connectIP = sys.env.getOrElse("POSTGRES_IP", "localhost").toString
  val connectPort = sys.env.getOrElse("POSTGRES_PORT", 5432).toString.toInt
  val database_user = sys.env.getOrElse("POSTGRES_USER", "postgres").toString
  val database_pw = sys.env.getOrElse("POSTGRES_PASSWORD", "postgres").toString
  val database_name = sys.env.getOrElse("POSTGRES_DB", "postgres").toString

  val database =
    Database.forURL(
      url = "jdbc:postgresql://" + connectIP + ":" + connectPort + "/" + database_name + "?serverTimezone=UTC",
      user = database_user,
      password = database_pw,
      driver = "org.postgresql.Driver")

  val playerTable = TableQuery[PlayerTable]
  val gameTable = TableQuery[GameTable]


  override def createGame(board: String): Int = ???

  override def read(gameId: Int): Option[String] =
    val query = sql"""SELECT * FROM "PLAYER" WHERE "id" = $gameId""".as[(Int, Int, String, Int, Int, Int, Int, Int, Int)]
    val result = Await.result(database.run(query), atMost = 10.second)
    result match {
      case Seq(a) => Option(a.toString)//maybe Some not Option
      case _ => None
    }

  override def update(board: String): String =
    val gameData = JsonHelper.jsonToDataObject(board)
    val query = sql"""UPDATE "PLAYER" SET "cell" = ${gameData.player(1).cell.number} """.as[Int]
    val result = Await.result(database.run(query), atMost = 10.second)
    result match {
      case Seq(a) => a.toString
      case _ => "error"
    }


  override def delete(gameId: Int): Future[Any] =
    val query = playerTable.filter(_.gameId === gameId).delete
    val query2 = gameTable.filter(_.gameId === gameId).delete
    Future(Await.result(database.run(query), atMost = 10.second))
    Future(Await.result(database.run(query2), atMost = 10.second))

  override def createDB(): Unit =
    val gameDB = Future(Await.result(database.run(
      DBIO.seq(
        playerTable.schema.createIfNotExists,
        gameTable.schema.createIfNotExists
      )), Duration.Inf))
    gameDB.onComplete {
      case Success(_) =>
        print("Connection to DB & Creation of Tables successful!")
        //initializeDatabase()
      case Failure(e) => print("\nError: " + e + "\n")
    }


