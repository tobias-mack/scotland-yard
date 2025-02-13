package fileIOComponent.databaseComponent.MongoDB

import com.google.inject.Inject
import fileIOComponent.databaseComponent.{DBInterface, GameData, GameDataPrototype, JsonHelper, GameDataDefaultPrototype}
import org.mongodb.scala.*
import org.mongodb.scala.model.Filters.*
import org.mongodb.scala.model.Updates.set
import org.mongodb.scala.result.{DeleteResult, InsertManyResult, InsertOneResult, UpdateResult}
import play.api.libs.json.{JsArray, JsValue, Json}
import modell.gameComponents.{Cell, Detective, GameInformation, MisterX, Player, Ticket}
import org.slf4j.Logger

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success, Try}


class MongoDBImpl @Inject() extends DBInterface :


  val database_pw = sys.env.getOrElse("MONGO_INITDB_ROOT_PASSWORD", "mongo").toString
  val database_username = sys.env.getOrElse("MONGO_INITDB_ROOT_USERNAME", "root").toString

  val uri: String = "mongodb://localhost:27017/"
  val client: MongoClient = MongoClient(uri)
  val db: MongoDatabase = client.getDatabase("ScotlandYard")
  val playerCollection: MongoCollection[Document] = db.getCollection("player")
  val gameCollection: MongoCollection[Document] = db.getCollection("game")

  var playerId_Max = 0
  var gameId_Max = 0 //id for gameInfo and foreign key for - player <-> game
  val logger: Logger = org.slf4j.LoggerFactory.getLogger(getClass)
  def defaultGameData : GameDataPrototype = GameDataDefaultPrototype()

  override def createDB(): Unit =
    val player1Document: Document = Document("_id" -> 1,
      "cell" -> 1, "name" -> "Player_1", "taxi" -> 5, "bus" -> 5, "sub" -> 5, "black" -> 5, "typ" -> 0)
    observerInsertion(playerCollection.insertOne(player1Document))

    val playerDocuments = (1 to 100) map { (i: Int) =>
      Document("_id" -> i, "name" -> ("Player_" + i), "cell" -> 1,
        "taxi" -> 5, "bus" -> 5, "sub" -> 5, "black" -> 5, "typ" -> 0)
    }

    observerInsertionMany(playerCollection.insertMany(playerDocuments))

    val boardDocuments = (1 to 100) map { (i: Int) =>
      Document("_id" -> i, "travelLog" -> "1,2", "revealCounter" -> 2, "currentPlayer" -> 1)
    }

    observerInsertionMany(gameCollection.insertMany(boardDocuments))

  override def createGame(board: String): Int =
    val gameData = JsonHelper.jsonToDataObject(board)
    gameId_Max += 1


    playerId_Max += 1
    val d1 = Document("_id" -> playerId_Max, "gameId" -> gameId_Max,
      "name" -> gameData.player(0).name, "cell" -> gameData.player(0).cell.number,
      "taxi" -> gameData.player(0).ticket.taxi, "bus" -> gameData.player(0).ticket.bus,
      "sub" -> gameData.player(0).ticket.subway, "black" -> gameData.player(0).ticket.black,
      "typ" -> gameData.player(0).typ)
    playerId_Max += 1
    val d2 = Document("_id" -> playerId_Max, "gameId" -> gameId_Max,
      "name" -> gameData.player(1).name, "cell" -> gameData.player(1).cell.number,
      "taxi" -> gameData.player(1).ticket.taxi, "bus" -> gameData.player(1).ticket.bus,
      "sub" -> gameData.player(1).ticket.subway, "black" -> gameData.player(1).ticket.black,
      "typ" -> gameData.player(1).typ)
    val playerDocuments = Vector[Document](d1, d2)
    observerInsertionMany(playerCollection.insertMany(playerDocuments))

    val gameDocument = Document("_id" -> gameId_Max, "gameId" -> gameId_Max,
      "travelLog" -> gameData.gameInfo.travelLog.toString, "revealCounter" -> gameData.gameInfo.revealCounter,
      "currentPlayer" -> gameData.gameInfo.currentPlayer)
    observerInsertion(gameCollection.insertOne(gameDocument))

    gameId_Max


  override def read(gameId: Int): Option[String] =
    //var game = GameData()
    var game = defaultGameData.cloneGameData()

    val playerResult = Await.result(playerCollection.find(equal("gameId", gameId)).toFuture(), Duration.Inf)
    playerResult.foreach(document => {
      val gameId = document.get("gameId").get.asNumber().intValue
      val typ = document.get("typ").get.asNumber().intValue
      val name = document.get("name").get.asString().getValue
      val cell = document.get("cell").get.asNumber().intValue
      val taxi = document.get("taxi").get.asNumber().intValue
      val bus = document.get("bus").get.asNumber().intValue
      val sub = document.get("sub").get.asNumber().intValue
      val black = document.get("black").get.asNumber().intValue

      if typ == 1 then
        game.player = game.player :+ MisterX(name, Cell(cell), Ticket(taxi, bus, sub, black))
      else
        game.player = game.player :+ Detective(name, Cell(cell), Ticket(taxi, bus, sub, black))
    })

    val gameResult = Await.result(gameCollection.find(equal("gameId", gameId)).toFuture(), Duration.Inf)
    gameResult.foreach(document => {
      val travelLogString = document.get("travelLog").get.asString().getValue
      val travelLog = ListBuffer[Int]()
      ("""\d+""".r findAllIn travelLogString).foreach(x => travelLog += x.toInt)
      val revealCounter = document.get("revealCounter").get.asNumber().intValue
      val currentPlayer = document.get("currentPlayer").get.asNumber().intValue

      game.gameInfo = GameInformation(travelLog, revealCounter, currentPlayer)
    })
    if game.gameInfo.revealCounter != 3 then
      JsonHelper.gameToJsonString(game, gameId)
    else
      Option("")

  override def update(board: String) =
    val gameData = JsonHelper.jsonToDataObject(board)
    Try({
      observerUpdate(gameCollection.replaceOne(equal("_id", gameData.gameId),
        Document("gameId" -> gameId_Max,
          "travelLog" -> gameData.gameInfo.travelLog.toString, "revealCounter" -> gameData.gameInfo.revealCounter,
          "currentPlayer" -> gameData.gameInfo.currentPlayer)))
      observerUpdate(playerCollection.updateOne(equal("_id", 1), set("cell", gameData.player(0).cell.number)))
      observerUpdate(playerCollection.updateOne(equal("_id", 2), set("cell", gameData.player(1).cell.number)))

    }) match {
      case Success(_) => "Success"
      case Failure(exception) => "Failure"
    }

  override def delete(gameId: Int): Unit =
    Try({
      observerDeletionMany(playerCollection.deleteMany(equal("gameId", gameId)))
      observerDeletionMany(gameCollection.deleteMany(equal("gameId", gameId)))
    }) match {
      case Success(_) => logger.info("Deletion successful")
      case Failure(exception) => logger.error("Deletion failed")
    }
  //Await.result(db.drop().toFuture(), Duration.Inf)


  private def observerInsertion(insertObservable: SingleObservable[InsertOneResult]): Unit = {
    insertObservable.subscribe(new Observer[InsertOneResult] {
      override def onNext(result: InsertOneResult): Unit = logger.info(s"inserted: $result")

      override def onError(e: Throwable): Unit = logger.error(s"onError: $e")

      override def onComplete(): Unit = logger.info("completed")
    })
  }

  private def observerInsertionMany(insertObservable: SingleObservable[InsertManyResult]): Unit = {
    insertObservable.subscribe(new Observer[InsertManyResult] {
      override def onNext(result: InsertManyResult): Unit = logger.info(s"inserted: $result")

      override def onError(e: Throwable): Unit = logger.error(s"onError: $e")

      override def onComplete(): Unit = logger.info("completed")
    })
  }

  private def observerDeletionMany(insertObservable: SingleObservable[DeleteResult]): Unit = {
    insertObservable.subscribe(new Observer[DeleteResult] {
      override def onNext(result: DeleteResult): Unit = logger.info(s"deleted: $result")

      override def onError(e: Throwable): Unit = logger.error(s"onError: $e")

      override def onComplete(): Unit = logger.info("completed")
    })
  }

  private def observerUpdate(insertObservable: SingleObservable[UpdateResult]): Unit = {
    insertObservable.subscribe(new Observer[UpdateResult] {
      override def onNext(result: UpdateResult): Unit = logger.info(s"inserted: $result")

      override def onError(e: Throwable): Unit = logger.error(s"onError: $e")

      override def onComplete(): Unit = logger.info("completed")
    })
  }


