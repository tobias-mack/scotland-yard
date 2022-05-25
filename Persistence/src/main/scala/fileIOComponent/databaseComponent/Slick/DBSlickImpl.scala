package fileIOComponent.databaseComponent.Slick

import com.google.inject.Inject
import fileIOComponent.databaseComponent.DBInterface
import fileIOComponent.databaseComponent.Slick.Tables.{GameTable, PlayerTable}
import play.api.libs.json.{JsArray, JsValue, Json}
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

  override def read(gameId: Int): Option[String] = ???

  override def update(board: String): String = ???

  override def delete(gameId: Int): Unit = ???

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


