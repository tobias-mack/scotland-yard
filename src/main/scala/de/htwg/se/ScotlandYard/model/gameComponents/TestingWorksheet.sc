import de.htwg.se.ScotlandYard.model.gameComponents.{Board, Ticket}

import scala.io.StdIn.readLine
import scalax.collection.Graph
import scalax.collection.GraphPredef
import scalax.collection.GraphEdge.{UnDiEdge, ~}
import scalax.collection.GraphPredef.EdgeAssoc


val board = Board()
board.cell
board.player
val x = Vector(1,2,1,2)
val y = x.updated(0,100) .updated(1,123)                // Vector(100, 2, 1, 2)

val g1 = Graph(UnDiEdge(10,1),UnDiEdge(1,2),UnDiEdge(2,10))   //10~1

def n(outer: Int): g1.NodeT = g1 get outer
def getNeighbours(position:Int):Set[g1.NodeT] = n(position).diSuccessors

var nb = getNeighbours(10)
def isPossible(set:Set[g1.NodeT],goToPos:Int):Boolean = {
  set.exists(x => x.value == goToPos)
}
if(isPossible(nb,1)) println("goToPos 1 is possible")
if(!isPossible(nb,3)) println("goToPos 3 is not possible")

val mapKN = Graph(10~1,1~20,20~2,1~2,2~3,2~5,3~4,4~5,4~6,5~6,5~7,5~8,7~9,
                8~21,21~9,9~13,10~13,9~10,10~11,11~12,12~13,12~14,13~14,
                14~15,15~16,16~6,16~7,16~19,16~18,15~17,17~18,18~19,19~20)
