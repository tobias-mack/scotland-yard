package fileIOComponent.databaseComponent.MongoDB

import com.google.inject.Inject
import fileIOComponent.databaseComponent.DAOInterface
import play.api.libs.json.{JsArray, JsValue, Json}
import org.mongodb.scala.*
import org.mongodb.scala.model.Updates.set
import org.mongodb.scala.model.Filters.*
import org.mongodb.scala.result.{DeleteResult, InsertOneResult, UpdateResult}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration


class MongoDBImpl @Inject ()  extends DAOInterface:

  val uri: String = "mongodb://mongo:27017/"
  val client: MongoClient = MongoClient(uri)
  val db: MongoDatabase = client.getDatabase("ConnectFour")
  val playerCollection: MongoCollection[Document] = db.getCollection("player")

  override def create =
    val player1Document: Document = Document("_id" -> "playerDocument1", "playerNum" -> 1, "playerName" -> "Player_1")
    val player2Document: Document = Document("_id" -> "playerDocument2", "playerNum" -> 2, "playerName" -> "Player_2")
    val player3Document: Document = Document("_id" -> "playerDocument3", "playerNum" -> 3, "playerName" -> "Player_3")
    val player4Document: Document = Document("_id" -> "playerDocument4", "playerNum" -> 4, "playerName" -> "Player_4")
    val player5Document: Document = Document("_id" -> "playerDocument5", "playerNum" -> 5, "playerName" -> "Player_5")

    observerInsertion(playerCollection.insertOne(player1Document))
    observerInsertion(playerCollection.insertOne(player2Document))
    observerInsertion(playerCollection.insertOne(player3Document))
    observerInsertion(playerCollection.insertOne(player4Document))
    observerInsertion(playerCollection.insertOne(player5Document))


  override def read: String =
    val playerDocument1: Document = Await.result(playerCollection.find(equal("_id", "playerDocument1")).first().head(), Duration.Inf)
    val playerDocument2: Document = Await.result(playerCollection.find(equal("_id", "playerDocument2")).first().head(), Duration.Inf)
    val playerDocument3: Document = Await.result(playerCollection.find(equal("_id", "playerDocument3")).first().head(), Duration.Inf)
    val playerDocument4: Document = Await.result(playerCollection.find(equal("_id", "playerDocument4")).first().head(), Duration.Inf)
    val playerDocument5: Document = Await.result(playerCollection.find(equal("_id", "playerDocument5")).first().head(), Duration.Inf)

    val player1 = playerDocument1("playerName").asString().toString
    val player2 = playerDocument2("playerName").asString().toString
    val player3 = playerDocument3("playerName").asString().toString
    val player4 = playerDocument4("playerName").asString().toString
    val player5 = playerDocument5("playerName").asString().toString
    null


  override def update(input: String) =
    val playerDocument1: Document = Await.result(playerCollection.find(equal("_id", "playerDocument1")).first().head(), Duration.Inf)
    val playerDocument2: Document = Await.result(playerCollection.find(equal("_id", "playerDocument2")).first().head(), Duration.Inf)
    val playerDocument3: Document = Await.result(playerCollection.find(equal("_id", "playerDocument3")).first().head(), Duration.Inf)
    val playerDocument4: Document = Await.result(playerCollection.find(equal("_id", "playerDocument4")).first().head(), Duration.Inf)
    val playerDocument5: Document = Await.result(playerCollection.find(equal("_id", "playerDocument5")).first().head(), Duration.Inf)


  override def delete =
    playerCollection.deleteMany(equal("_id", "playerDocument")).subscribe(
      (dr: DeleteResult) => println(s"Deleted playerDocument"),
      (e: Throwable) => println(s"Error while trying to delete playerDocument: $e")
    )






  private def observerInsertion(insertObservable: SingleObservable[InsertOneResult]): Unit = {
    insertObservable.subscribe(new Observer[InsertOneResult] {
      override def onNext(result: InsertOneResult): Unit = println(s"inserted: $result")

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


