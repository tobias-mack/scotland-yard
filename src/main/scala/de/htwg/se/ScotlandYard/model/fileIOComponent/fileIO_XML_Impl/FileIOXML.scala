
package de.htwg.se.ScotlandYard.model.fileIOComponent.fileIO_XML_Impl

import com.google.inject.Guice
import com.google.inject.name.Names
import de.htwg.se.ScotlandYard.ScotlandYard.controller
import de.htwg.se.ScotlandYard.ScotlandYard.controller.board
import de.htwg.se.ScotlandYard.controller.ControllerInterface
import de.htwg.se.ScotlandYard.model.BoardInterface
import de.htwg.se.ScotlandYard.model.fileIOComponent.FileIOInterface
import de.htwg.se.ScotlandYard.model.gameComponents.{Cell, Player, Ticket}
import net.codingwell.scalaguice.InjectorExtensions._
import scalax.collection.GraphPredef.anyToNode

import java.io.PrintWriter
import scala.xml.{Elem, PrettyPrinter}


class FileIOXML extends FileIOInterface {


  override def load(controller : ControllerInterface): Unit = {
    val file = scala.xml.XML.loadFile("board.xml")
    val p1 = file \\ "Player1"
    val p2 = file \\ "Player2"

    val p1n = (p1 \ "@name").text
    val p1c = (p1 \ "@cell").text
    val p1t1 = (p1 \ "@ticket1").text
    val p1t2 = (p1 \ "@ticket2").text
    val p1t3 = (p1 \ "@ticket3").text
    val p1t4 = (p1 \ "@ticket4").text
    val p1ty = (p1 \ "@type").text

    val p2n = (p2 \ "@name").text
    val p2c = (p2 \ "@cell").text
    val p2t1 = (p2 \ "@ticket1").text
    val p2t2 = (p2 \ "@ticket2").text
    val p2t3 = (p2 \ "@ticket3").text
    val p2t4 = (p2 \ "@ticket4").text
    val p2ty = (p2 \ "@type").text

    for {
      n <- 0 until controller.playerAdded
    } yield controller.undo()

    controller.board.addDetective(board, p1n,Cell(p1c.toInt), Ticket(p1t1.toInt,p1t2.toInt,p1t3.toInt,p1t4.toInt))
    controller.board.addDetective(board, p2n,Cell(p2c.toInt), Ticket(p2t1.toInt,p2t2.toInt,p2t3.toInt,p2t4.toInt))
  }


  override def save(controller: ControllerInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("board.xml"))
    val prettyPrinter = new PrettyPrinter(120,6)
    val xml = prettyPrinter.format(saveGame(controller))
    pw.write(xml)
    pw.close()
  }

  def saveGame(controller: ControllerInterface): Elem = {
    <game>
    {playersToXML()}
    </game>
  }

  def playersToXML(): Elem = {
    val player1 = controller.board.player(0)
    val player2 = controller.board.player(1)

    <players>
      <p1>
        <name>{player1.name}</name>
        <cell>{player1.cell}</cell>
        <ticket1>{player1.ticket.taxi}</ticket1>
        <ticket2>{player1.ticket.bus}</ticket2>
        <ticket3>{player1.ticket.subway}</ticket3>
        <ticket4>{player1.ticket.black}</ticket4>
        <type>{player1.typ}</type>
      </p1>
      <p2>
        <name>{player2.name}</name>
        <cell>{player2.cell}</cell>
        <ticket1>{player2.ticket.taxi}</ticket1>
        <ticket2>{player2.ticket.bus}</ticket2>
        <ticket3>{player2.ticket.subway}</ticket3>
        <ticket4>{player2.ticket.black}</ticket4>
        <type>{player2.typ}</type>
      </p2>

    </players>
  }



/*

  def saveString(board: BoardInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("board.xml"))
    val prettyPrinter = new PrettyPrinter(1,1)
    var sb = new StringBuilder
    val xml = prettyPrinter.format(boardToXML(board))
    pw.write(xml)
    pw.close()

  }


  def saveXML(board: BoardInterface): Unit = {
    scala.xml.XML.save("board.xml", boardToXML(board))
  }


  def boardToXML(board: BoardInterface): Elem = {
    <board player1={
    for {
      n <- board.player.indices
    } yield playerToXML()
           }>
    </board>

  def playerToXML(): Elem = {
    con
    <players>
      <p1>
        <name>{board.player}</name>
      </p1>
    </players>
      //<player name={board.player(n).name} cell={board.player(n).cell} ticket={board.player(n).ticket} typ={board.player(n).typ}>
      //  { board.player(n).value }
      //</player>
    }
  }
*/

}

