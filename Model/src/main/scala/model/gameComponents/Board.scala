package model.gameComponents

import akka.http.javadsl.unmarshalling.Unmarshaller
import akka.http.scaladsl.util.FastFuture
import akka.stream.Materializer
import com.google.inject.Inject
import com.google.inject.name.Named
import model.BoardInterface
import play.api.libs.json.{JsValue, Json}
import scalax.collection.Graph
import scalax.collection.GraphEdge.UnDiEdge
import scalax.collection.GraphPredef.EdgeAssoc

import scala.collection.immutable.{BitSet, HashMap}
import scala.collection.mutable.ListBuffer
import scala.concurrent.{ExecutionContext, Future}
import scala.util.control.NonFatal

case class Board @Inject()(@Named("DefaultPlayer") player1: Vector[Player] = Vector[Player](), @Named("DefaultGameInfo") gameInformation: GameInformation = GameInformation()) extends BoardInterface :

	val player: Vector[Player] = player1

	val gameInfo: GameInformation = gameInformation

	val mapKN: Graph[Int, UnDiEdge] = Graph(10 ~ 1, 1 ~ 20, 20 ~ 2, 1 ~ 2, 2 ~ 3, 2 ~ 5, 3 ~ 4, 4 ~ 5, 4 ~ 6, 5 ~ 6, 5 ~ 7, 5 ~ 8, 7 ~ 9,
		7 ~ 8, 8 ~ 21, 21 ~ 9, 9 ~ 13, 10 ~ 13, 9 ~ 10, 10 ~ 11, 11 ~ 12, 12 ~ 13, 12 ~ 14, 13 ~ 14,
		14 ~ 15, 15 ~ 16, 16 ~ 6, 16 ~ 7, 16 ~ 19, 16 ~ 18, 15 ~ 17, 17 ~ 18, 18 ~ 19, 19 ~ 20)
	val mapKNTaxi: Graph[Int, UnDiEdge] = Graph(10 ~ 1, 1 ~ 20, 2 ~ 3, 2 ~ 5, 3 ~ 4, 4 ~ 5, 5 ~ 6, 5 ~ 7, 5 ~ 8,
		7 ~ 8, 9 ~ 13, 10 ~ 13, 9 ~ 10, 12 ~ 13, 12 ~ 14, 13 ~ 14, 15 ~ 16, 16 ~ 6, 16 ~ 7, 16 ~ 19, 16 ~ 18, 18 ~ 19)
	val mapKNBus: Graph[Int, UnDiEdge] = Graph(10 ~ 1, 1 ~ 20, 20 ~ 2, 1 ~ 2, 4 ~ 6, 5 ~ 8,
		7 ~ 8, 8 ~ 21, 21 ~ 9, 10 ~ 11, 11 ~ 12, 12 ~ 14, 15 ~ 16, 16 ~ 6, 16 ~ 18, 15 ~ 17, 17 ~ 18)
	val mapKNSub: Graph[Int, UnDiEdge] = Graph(7 ~ 9, 14 ~ 15)

	val taxiLocations: BitSet = BitSet(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14, 15, 16, 18, 19, 20)
	val busLocations: BitSet = BitSet(1, 2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 16, 17, 18, 20, 21)
	val subLocations: BitSet = BitSet(7, 9, 14, 15)

	val transportNumToMap: Map[Int, Graph[Int, UnDiEdge]] = HashMap(1 -> mapKNTaxi, 2 -> mapKNBus, 3 -> mapKNSub, 4 -> mapKN)

	def n(map: Graph[Int, UnDiEdge]): Int => map.NodeT = (outer: Int) => map get outer

	def nBigMap: Int => Graph[Int, UnDiEdge]#NodeT = n(mapKN) //partially applied function

	def getNeighbours(map: Graph[Int, UnDiEdge], position: Int): Set[map.NodeT] = n(map)(position).diSuccessors

	def isPossible(map: Graph[Int, UnDiEdge], set: Set[map.NodeT], goToPos: Int): Boolean = set.exists(x => x.value == goToPos)

	def checkTransport(transport: Int, currentOrder: Int): Boolean =
		transport match
			case 1 => this.player(currentOrder).ticket.taxi > 0 && taxiLocations(player(currentOrder).cell.number)
			case 2 => this.player(currentOrder).ticket.bus > 0 && busLocations(player(currentOrder).cell.number)
			case 3 => this.player(currentOrder).ticket.subway > 0 && subLocations(player(currentOrder).cell.number)
			case 4 => true

	def checkPossDest(position: Int, transport: Int, currentOrder: Int): Boolean =
		val map = transportNumToMap.get(transport)
		map match
			case Some(map) => isPossible(map, getNeighbours(map, player(currentOrder).cell.number), position)
			case None => false

	def checkLoosing(): Boolean = player.exists(p => !p.equals(this.player(0)) && p.ticket.isEmpty())

	def checkWinning(): Boolean = player.exists(p => !p.equals(player(0)) && p.cell.number.equals(player(0).cell.number))

	def addDetective(board: BoardInterface, newName: String, cell: Cell, ticket: Ticket): Board =
		val MrX = MisterX(newName, cell, ticket)
		val newPlayer = Detective(newName, cell, ticket)
		val newBoard =
			Board(
				player1 =
					if board.player.isEmpty then board.player :+ MrX
					else board.player :+ newPlayer,
				gameInfo)
		newBoard


	def boardToJson(board: BoardInterface): JsValue =
		Json.obj(
			"gameInformation" -> Json.obj(
				"currentPlayer" -> board.gameInfo.currentPlayer,
				"travelLog" -> board.gameInfo.travelLog.mkString(" "),
				"revealCounter" -> board.gameInfo.revealCounter,
				"playerNumber" -> board.player.size
			),
			"players" -> Json.toJson(
				for player <- board.player
					yield Json.obj(
						"name" -> player.name,
						"cell" -> player.cell.number,
						"typ" -> player.typ,
						"ticket" -> Json.obj(
							"taxi" -> player.ticket.taxi,
							"bus" -> player.ticket.bus,
							"subway" -> player.ticket.subway,
							"black" -> player.ticket.black,
						)
					)
			)
		)


	override def jsonToBoard(value: String): BoardInterface =
		val json: JsValue = Json.parse(value)

		val info = json \ "gameInformation"
		val travelLog = ListBuffer[Int]()
		val travelLogString = info.get("travelLog").toString()
		("""\d+""".r findAllIn travelLogString).foreach(x => travelLog += x.toInt)
		val loadedGameInfo = GameInformation(
			travelLog,
			info.get("revealCounter").toString().toInt,
			info.get("currentPlayer").toString().toInt
		)

		val playerNumber = info.get("playerNumber").toString().toInt
		val players = json \ "players"
		val loadedPlayers: Vector[Player] =
			(0 until playerNumber).map(i =>
				if i == 0 then
					MisterX((players(i) \ "name").as[String],
						Cell((players(i) \ "cell").as[Int]),
						Ticket((players(i) \ "ticket").get("taxi").toString().toInt,
							(players(i) \ "ticket").get("bus").toString().toInt,
							(players(i) \ "ticket").get("subway").toString().toInt,
							(players(i) \ "ticket").get("black").toString().toInt)
					)
				else
					Detective((players(i) \ "name").as[String],
						Cell((players(i) \ "cell").as[Int]),
						Ticket((players(i) \ "ticket").get("taxi").toString().toInt,
							(players(i) \ "ticket").get("bus").toString().toInt,
							(players(i) \ "ticket").get("subway").toString().toInt,
							(players(i) \ "ticket").get("black").toString().toInt)
					)
			).toVector

		Board(loadedPlayers, loadedGameInfo)

	override def toJsonString: String =
		Json.prettyPrint(boardToJson(this))

	override def toJson: JsValue =
		boardToJson(this)

	override def toString: String =
		val board = new StringBuilder("  BOARD: ")
		board.append("\n \u001b[33m" + "Taxi-Locations: ")
		taxiLocations.foreach(x => board.append(s"$x "))
		board.append("\n \u001b[32m" + "Bus-Locations: ")
		busLocations.foreach(x => board.append(s"$x "))
		board.append("\n \u001b[31m" + "Subway-Locations: ")
		subLocations.foreach(x => board.append(s"$x "))
		board.append("\n")
		player.map(n => board.append("  \u001b[30m" + n.name + " \u001b[0mis at \u001b[34mposition " + n.cell.number + " \u001b[0mand has\u001b[33m " +
			n.ticket.taxi + " Taxi \u001b[0mtickets,\u001b[32m" + n.ticket.bus + " Bus \u001b[0mtickets, \u001b[31m" +
			n.ticket.subway + " Subway \u001b[0mtickets" + "; \n"))
		board.toString()


