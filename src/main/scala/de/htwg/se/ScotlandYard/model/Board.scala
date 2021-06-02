package de.htwg.se.ScotlandYard.model

import scalax.collection.Graph
import scalax.collection.GraphEdge.{UnDiEdge, ~}
import scalax.collection.GraphPredef.EdgeAssoc

case class Board( cell : Vector[Cell] = Vector[Cell](Cell(1),Cell(2),Cell(3)),
                  player: Vector[Player] = Vector[Player]()) {

  val mapKN: Graph[Int, UnDiEdge] = Graph(10~1,1~20,20~2,1~2,2~3,2~5,3~4,4~5,4~6,5~6,5~7,5~8,7~9,
    8~21,21~9,9~13,10~13,9~10,10~11,11~12,12~13,12~14,13~14,
    14~15,15~16,16~6,16~7,16~19,16~18,15~17,17~18,18~19,19~20)

  def n(outer: Int): mapKN.NodeT = mapKN get outer
  def getNeighbours(position:Int):Set[mapKN.NodeT] = n(position).diSuccessors
  def isPossible(set:Set[mapKN.NodeT],goToPos:Int):Boolean = {
    set.exists(x => x.value == goToPos)
  }
  def checkPossDest(position: Int,transport: Int,currentPos: Int):Boolean = {
    val nb = getNeighbours(player(currentPos).cell.number)
    isPossible(nb,position)
  }

  def addDetective(board: Board, newName: String): Board = {
    val MrX = MisterX(name = newName)
    val newPlayer = Detective(name = newName)
    val newBoard = board.copy(player = if(board.player.isEmpty){board.player :+ MrX} else{board.player :+ newPlayer})
    newBoard
  }

  override def toString: String = {
    val board = new StringBuilder("  Board: ")
    for (n <- cell){
      val append = n.number
      board.append(s"$append , ")
    }
    board.setLength(board.length() - 2)
    val Statement = new StringBuilder()
    for (n <- player){
      Statement.append("  \u001b[30m" + n.name + " \u001b[0mis at \u001b[34mposition " + n.cell.number + " \u001b[0mand has\u001b[33m " +
        n.ticket.taxi + " Taxi \u001b[0mtickets,\u001b[32m" + n.ticket.bus + " Bus \u001b[0mtickets, \u001b[31m" +
        n.ticket.subway + " Subway \u001b[0mtickets" + "; \n")
    }
    Statement.append(board)
    Statement.toString()
  }
   def movePlayer(board: Board, pos: Int, playerNumber: Int,transport: Int): Board = {
    transport match {
      case 1 => strategy1(board, pos, playerNumber)
      case 2 => strategy2(board, pos, playerNumber)
      case 3 => strategy3(board, pos, playerNumber)
      case 4 => strategy4(board, pos, playerNumber)
    }
  }
  def strategy1(board: Board, pos: Int, playerNumber: Int): Board = {
    val Player = board.player(playerNumber)
    val newData = Player.setCell(Player, Cell(pos),Ticket(Player.ticket.taxi - 1,Player.ticket.bus,Player.ticket.subway,Player.ticket.black))
    val newPlayer = board.player.updated(playerNumber, newData)
    val newBoard = board.copy(player = newPlayer)
    newBoard
  }

  def strategy2(board: Board, pos: Int, playerNumber: Int): Board = {
    val Player = board.player(playerNumber)
    val newData = Player.setCell(Player, Cell(pos), Ticket(Player.ticket.taxi, Player.ticket.bus - 1, Player.ticket.subway, Player.ticket.black))
    val newPlayer = board.player.updated(playerNumber, newData)
    val newBoard = board.copy(player = newPlayer)
    newBoard
  }

  def strategy3(board: Board, pos: Int, playerNumber: Int): Board = {
    val Player = board.player(playerNumber)
    val newData = Player.setCell(Player, Cell(pos),Ticket(Player.ticket.taxi,Player.ticket.bus,Player.ticket.subway - 1,Player.ticket.black))
    val newPlayer = board.player.updated(playerNumber, newData)
    val newBoard = board.copy(player = newPlayer)
    newBoard
  }

  def strategy4(board: Board, pos: Int, playerNumber: Int): Board = {
    val Player = board.player(playerNumber)
    val newData = Player.setCell(Player, Cell(pos),Ticket(Player.ticket.taxi,Player.ticket.bus,Player.ticket.subway,Player.ticket.black - 1))
    val newPlayer = board.player.updated(playerNumber, newData)
    val newBoard = board.copy(player = newPlayer)
    newBoard
  }

}
