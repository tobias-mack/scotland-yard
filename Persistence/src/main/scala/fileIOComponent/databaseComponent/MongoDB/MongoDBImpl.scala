package fileIOComponent.databaseComponent.MongoDB

import com.google.inject.Inject
import fileIOComponent.databaseComponent.DAOInterface
import play.api.libs.json.{JsArray, JsValue, Json}
import org.mongodb.scala.*
import org.mongodb.scala.model.Updates.set
import org.mongodb.scala.model.Filters.*
import org.mongodb.scala.result.{DeleteResult, InsertManyResult, InsertOneResult, UpdateResult}

import scala.collection.mutable
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration


class MongoDBImpl @Inject() extends DAOInterface :

  val database_pw = sys.env.getOrElse("MONGO_INITDB_ROOT_PASSWORD", "mongo").toString
  val database_username = sys.env.getOrElse("MONGO_INITDB_ROOT_USERNAME", "root").toString

  val uri: String = "mongodb://localhost:27017/"
  val client: MongoClient = MongoClient(uri)
  val db: MongoDatabase = client.getDatabase("ScotlandYard")
  val playerCollection: MongoCollection[Document] = db.getCollection("player")
  val boardCollection: MongoCollection[Document] = db.getCollection("board")

  override def create =
    //val player1Document: Document = Document("_id" -> 1,
    //  "cell" -> 1, "name" -> "Player_1", "taxi" -> 5, "bus" -> 5, "sub" -> 5, "black" -> 5, "typ" -> 0)
    //observerInsertion(playerCollection.insertOne(player1Document))

    val playerDocuments = (1 to 100) map { (i: Int) =>
      Document("_id" -> i, "name" -> ("Player_" + i), "cell" -> 1,
        "taxi" -> 5, "bus" -> 5, "sub" -> 5, "black" -> 5, "typ" -> 0)
    }

    observerInsertionMany(playerCollection.insertMany(playerDocuments))

    val boardDocuments = (1 to 100) map { (i: Int) =>
      Document("_id" -> i, "travelLog" -> "1,2", "revealCounter" -> 2, "currentPlayer" -> 1)
    }

    observerInsertionMany(boardCollection.insertMany(boardDocuments))


  override def read: String =
    //val playerDoc: Document = Await.result(playerCollection.find(equal("_id", "playerDocument1")).first().head(), Duration.Inf)
    //playerDoc.toJson()
    val s = new StringBuilder()
    val observableResult = Await.result(playerCollection.find().toFuture(), Duration.Inf)

    observableResult.foreach(document => {
      val name: String = document.get("name").get.asString().getValue
      val cell: Int = document.get("cell").get.asNumber().intValue
      s.append(name + " is at cell " + cell + "\n")
    })
    s.toString()

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


