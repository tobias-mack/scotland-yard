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


class DBSlickImpl @Inject() extends DBInterface {

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


  override def createDB(): Unit =
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


  override def updatePlayer(playerId: Int, position: Int): String =
    if readPlayer(playerId).isEmpty then
      return "Player does not exist"
    val query = sql"""UPDATE "PLAYER" SET "cell" = $position WHERE "id" = $playerId""".as[(Int, String, Int, Int, Int, Int, Int, Int)]
    val result = Await.result(database.run(query), atMost = 10.second)
    result.toString

  override def createPlayer(playerID: Int, name: String): Int =
    Try({
      database.run(playerTable += (playerID, player.name, player.cell.number, player.ticket.taxi, player.ticket.bus, player.ticket.subway, player.ticket.black, player.typ))
      //player.playerNumber
    }) match {
      case Success(_) =>
        println("Success")
        player.typ // change this later maybe to name or number
      case Failure(exception) => println(exception); -1
    }

  override def updateGame(gameId: Int, travelLog: String, revealCounter: Int, currentPlayer: Int): String =
    val query =
      sql"""UPDATE "GAME" 
            SET "travelLog" = $travelLog, "revealCounter" = $revealCounter, "currentPlayer" = $currentPlayer 
            WHERE "id" = $gameId""".as[(Int, String, Int, Int)]
    val result = Await.result(database.run(query), atMost = 10.second)
    result.toString()

  override def readPlayer(playerId: Int): Option[(Int, String, Int, Int, Int, Int, Int, Int)] =
    val query = sql"""SELECT * FROM "PLAYER" WHERE "id" = $playerId""".as[(Int, String, Int, Int, Int, Int, Int, Int)]
    val result = Await.result(database.run(query), atMost = 10.second)
    result match {
      case Seq(a) => Option((a._1, a._2, a._3, a._4, a._5, a._6, a._7, a._8)) //maybe Some not Option
      case _ => None
    }


  override def deletePlayer(playerId: Int): Future[Any] =
    val query = playerTable.filter(_.id === playerId).delete
    Future(Await.result(database.run(query), atMost = 10.second))

  override def readAllPlayer(): List[(Int, String, Int, Int, Int, Int, Int, Int)] =
    val query = sql"""SELECT * FROM "PLAYER" """.as[(Int, String, Int, Int, Int, Int, Int, Int)]
    val result = Await.result(database.run(query), atMost = 10.second)
    result.toList

  override def readAllGames(): List[(Int, String, Int, Int)] =
    val query = sql"""SELECT * FROM "GAME" """.as[(Int, String, Int, Int)]
    val result = Await.result(database.run(query), atMost = 10.second)
    result.toList
  
  def delete: Unit =

    val action = playerTable.delete
    Await.result(database.run(action), atMost = 10.second)
    val deleteQuery = sql"""ALTER SEQUENCE "PLAYER_id_seq" RESTART WITH 1""".as[Int]
    Await.result(database.run(deleteQuery), atMost = 10.second)
    val action2 = gameTable.delete
    Await.result(database.run(action2), atMost = 10.second)
    val deleteQuery2 = sql"""ALTER SEQUENCE "GAME_id_seq" RESTART WITH 1""".as[Int]
    Await.result(database.run(deleteQuery2), atMost = 10.second)

}
