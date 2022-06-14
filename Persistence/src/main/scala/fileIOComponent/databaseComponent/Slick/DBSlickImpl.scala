package fileIOComponent.databaseComponent.Slick

import com.google.inject.Inject
import fileIOComponent.databaseComponent.{DBInterface, GameData, GameDataPrototype, JsonHelper, GameDataDefaultPrototype}
import fileIOComponent.databaseComponent.Slick.Tables.{GameTable, PlayerTable}
import modell.gameComponents.{Cell, Detective, GameInformation, MisterX, Ticket}
import play.api.libs.json.{JsArray, JsValue, Json}

import scala.collection.mutable.ListBuffer
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


class DBSlickImpl @Inject() extends DBInterface :

  val connectIP = sys.env.getOrElse("POSTGRES_IP", "localhost").toString
  val connectPort = sys.env.getOrElse("POSTGRES_PORT", 5432).toString.toInt
  val database_user = sys.env.getOrElse("POSTGRES_USER", "postgres").toString
  val database_pw = sys.env.getOrElse("POSTGRES_PASSWORD", "postgres").toString // "password"
  val database_name = sys.env.getOrElse("POSTGRES_DB", "postgres").toString

  val database =
    Database.forURL(
      url = "jdbc:postgresql://" + connectIP + ":" + connectPort + "/" + database_name + "?serverTimezone=UTC",
      user = database_user,
      password = database_pw,
      driver = "org.postgresql.Driver")

  val playerTable = TableQuery[PlayerTable]
  val gameTable = TableQuery[GameTable]

  var playerId_ONE = 0
  var playerId_TWO = 1
  var gameId_Max = 0 //id for gameInfo and foreign key for - player <-> game
  def defaultGameData : GameDataPrototype = GameDataDefaultPrototype()

  override def createGame(board: String): Int =
    this.createDB()
    val gameData = JsonHelper.jsonToDataObject(board)
    gameId_Max += 1

    val initializer = Future(Await.result(database.run(
      DBIO.seq(
        gameTable += (gameId_Max, gameId_Max, gameData.gameInfo.travelLog.toString,
          gameData.gameInfo.revealCounter, gameData.gameInfo.currentPlayer),
        playerTable ++= Seq(
          (playerId_ONE, gameId_Max, gameData.player(0).name, gameData.player(0).cell.number,
            gameData.player(0).ticket.taxi, gameData.player(0).ticket.bus,
            gameData.player(0).ticket.subway, gameData.player(0).ticket.black,
            gameData.player(0).typ),
          (playerId_TWO, gameId_Max, gameData.player(1).name, gameData.player(1).cell.number,
            gameData.player(1).ticket.taxi, gameData.player(1).ticket.bus,
            gameData.player(1).ticket.subway, gameData.player(1).ticket.black,
            gameData.player(1).typ)
        )
      )), Duration.Inf))
    initializer.onComplete {
      case Success(_) =>
        playerId_ONE += 1
        playerId_TWO += 1
        print("\ninitialized Database!")
      case Failure(e) => print("\nError: " + e + "\n")
    }
    //Await.result(database.run(gameTable += (0, "2", 3, 0)), atMost = 10.second)
    //Await.result(database.run(playerTable += (0, "mrX", 2, 5, 5, 5, 5, 1)), atMost = 10.second)

    gameId_Max

  override def read(gameId: Int): Option[String] =
    //var game = GameData()
    var game = defaultGameData.cloneGameData()

    val playerQuery = sql"""SELECT * FROM "PLAYER" WHERE "id" = $gameId""".as[(Int, Int, String, Int, Int, Int, Int, Int, Int)]
    val playerResult = Await.result(database.run(playerQuery), atMost = 10.second)
    playerResult.foreach(player => {
      //val id = player._1
      //val gameId = player._2
      val name = player._3
      val cell = player._4
      val taxi = player._5
      val bus = player._6
      val sub = player._7
      val black = player._8
      val typ = player._9

      if typ == 1 then
        game.player = game.player :+ MisterX(name, Cell(cell), Ticket(taxi, bus, sub, black))
      else
        game.player = game.player :+ Detective(name, Cell(cell), Ticket(taxi, bus, sub, black))
    })

    val gameQuery = sql"""SELECT * FROM "GAME" WHERE "id" = $gameId""".as[(Int, Int, String, Int, Int)]
    val gameResult = Await.result(database.run(gameQuery), atMost = 10.second)
    if gameResult.size == 1 then
      val travelLogString = gameResult(0)._3
      val travelLog = ListBuffer[Int]()
      ("""\d+""".r findAllIn travelLogString).foreach(x => travelLog += x.toInt)
      val revealCounter = gameResult(0)._4
      val currentPlayer = gameResult(0)._5

      game.gameInfo = GameInformation(travelLog, revealCounter, currentPlayer)

    if game.gameInfo.revealCounter != 3 then
      JsonHelper.gameToJsonString(game, gameId)
    else
      Option("")

  override def update(board: String): String =
    val gameData = JsonHelper.jsonToDataObject(board)
    val updateP1 =
      sql"""UPDATE "PLAYER" SET "cell" = ${gameData.player(0).cell.number}
        WHERE "id" = 1""".as[Int]
    val updateP2 =
      sql"""UPDATE "PLAYER" SET "cell" = ${gameData.player(1).cell.number}
        WHERE "id" = 2""".as[Int]
    val updateGame =
      sql"""UPDATE "GAME"
            SET "travelLog" = ${gameData.gameInfo.travelLog.toString()},
            "revealCounter" = ${gameData.gameInfo.revealCounter},
            "currentPlayer" = ${gameData.gameInfo.currentPlayer}
      WHERE "gameId" = ${gameData.gameId}""".as[Int]
    Try({
      val p1Result = Await.result(database.run(updateP1), atMost = 10.second)
      val p2Result = Await.result(database.run(updateP2), atMost = 10.second)
      val gameResult = Await.result(database.run(updateGame), atMost = 10.second)
    }) match {
      case Success(_) => "Success"
      case Failure(exception) => "Failure"
    }


  override def delete(gameId: Int): Unit =
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
      case Failure(e) => print("\nError: " + e + "\n")
    }


