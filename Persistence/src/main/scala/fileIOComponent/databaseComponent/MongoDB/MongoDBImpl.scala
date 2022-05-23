package fileIOComponent.databaseComponent.MongoDB

import com.google.inject.Inject
import fileIOComponent.databaseComponent.{DBInterface, GameData, JsonHelper}
import org.mongodb.scala.*
import org.mongodb.scala.model.Filters.*
import org.mongodb.scala.model.Updates.set
import org.mongodb.scala.result.{DeleteResult, InsertManyResult, InsertOneResult, UpdateResult}
import play.api.libs.json.{JsArray, JsValue, Json}
import modell.gameComponents.{Cell, Detective, GameInformation, MisterX, Player, Ticket}

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


	override def createDB = ???
	//val player1Document: Document = Document("_id" -> 1,
	//  "cell" -> 1, "name" -> "Player_1", "taxi" -> 5, "bus" -> 5, "sub" -> 5, "black" -> 5, "typ" -> 0)
	//observerInsertion(playerCollection.insertOne(player1Document))

	//val playerDocuments = (1 to 100) map { (i: Int) =>
	//	Document("_id" -> i, "name" -> ("Player_" + i), "cell" -> 1,
	//		"taxi" -> 5, "bus" -> 5, "sub" -> 5, "black" -> 5, "typ" -> 0)
	//}

	//observerInsertionMany(playerCollection.insertMany(playerDocuments))

	//val boardDocuments = (1 to 100) map { (i: Int) =>
	//	Document("_id" -> i, "travelLog" -> "1,2", "revealCounter" -> 2, "currentPlayer" -> 1)
	//}

	//observerInsertionMany(gameCollection.insertMany(boardDocuments))

	override def createGame(board: String): Int =
		val gameData = JsonHelper.jsonToDataObject(board)
		gameId_Max += 1

		val playerDocuments = gameData.player map (player =>
			playerId_Max += 1
				Document("_id" -> playerId_Max, "gameId" -> gameId_Max,
				"name" -> player.name, "cell" -> player.cell.number,
				"taxi" -> player.ticket.taxi, "bus" -> player.ticket.bus,
				"sub" -> player.ticket.subway, "black" -> player.ticket.black,
				"typ" -> player.typ))
		observerInsertionMany(playerCollection.insertMany(playerDocuments))

		val gameDocument = Document("_id" -> gameId_Max, "gameId" -> gameId_Max,
			"travelLog" -> gameData.gameInfo.travelLog.toString, "revealCounter" -> gameData.gameInfo.revealCounter,
			"currentPlayer" -> gameData.gameInfo.currentPlayer)
		observerInsertion(gameCollection.insertOne(gameDocument))

		gameId_Max


	override def read(gameId: Int): Option[String] =
		var game = GameData()

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
				game.player :+ MisterX(name, Cell(cell), Ticket(taxi, bus, sub, black))
			else
				game.player :+ Detective(name, Cell(cell), Ticket(taxi, bus, sub, black))
		})

		val gameResult = Await.result(gameCollection.find(equal("gameId", gameId)).toFuture(), Duration.Inf)
		playerResult.foreach(document => {
			val travelLogString = document.get("travelLog").get.asString().getValue
			val travelLog = ListBuffer[Int]()
			("""\d+""".r findAllIn travelLogString).foreach(x => travelLog += x.toInt)
			val revealCounter = document.get("revealCounter").get.asNumber().intValue
			val currentPlayer = document.get("currentPlayer").get.asNumber().intValue

			game.gameInfo = GameInformation(travelLog, revealCounter, currentPlayer)
		})

		JsonHelper.gameToJsonString(game)


	override def update(board: String) = ???

	override def delete(gameId: Int): Future[Any] = ???
	//playerCollection.deleteMany(equal("_id", "playerDocument")).subscribe(
	//	(dr: DeleteResult) => println(s"Deleted playerDocument"),
	//	(e: Throwable) => println(s"Error while trying to delete playerDocument: $e")
	//)


	private def observerInsertion(insertObservable: SingleObservable[InsertOneResult]): Unit = {
		insertObservable.subscribe(new Observer[InsertOneResult] {
			override def onNext(result: InsertOneResult): Unit = println(s"inserted: $result")

			override def onError(e: Throwable): Unit = println(s"onError: $e")

			override def onComplete(): Unit = println("completed")
		})
	}

	private def observerInsertionMany(insertObservable: SingleObservable[InsertManyResult]): Unit = {
		insertObservable.subscribe(new Observer[InsertManyResult] {
			override def onNext(result: InsertManyResult): Unit = println(s"inserted: $result")

			override def onError(e: Throwable): Unit = println(s"onError: $e")

			override def onComplete(): Unit = println("completed")
		})
	}

	private def observerUpdate(insertObservable: SingleObservable[UpdateResult]): Unit = {
		insertObservable.subscribe(new Observer[UpdateResult] {
			override def onNext(result: UpdateResult): Unit = println(s"inserted: $result")

			override def onError(e: Throwable): Unit = println(s"onError: $e")

			override def onComplete(): Unit = println("completed")
		})
	}


