
package de.htwg.se.ScotlandYard.model.fileIOComponent.fileIO_XML_Impl

import com.google.inject.Guice
import com.google.inject.name.Names
import de.htwg.se.ScotlandYard.ScotlandYard.controller
import de.htwg.se.ScotlandYard.ScotlandYard.controller.board
import de.htwg.se.ScotlandYard.controller.ControllerInterface
import de.htwg.se.ScotlandYard.model.BoardInterface
import de.htwg.se.ScotlandYard.model.fileIOComponent.FileIOInterface
import de.htwg.se.ScotlandYard.model.gameComponents.Player
import net.codingwell.scalaguice.InjectorExtensions._
import scalax.collection.GraphPredef.anyToNode

import java.io.PrintWriter
import scala.xml.{Elem, PrettyPrinter}


class FileIOXML extends FileIOInterface {


  override def load: ControllerInterface = ???


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
        <name> {player1.name}</name>
        <cell> {player1.cell}</cell>
        <ticket> { player1.ticket}</ticket>
        <type> { player1.typ}</type>
      </p1>
      <p2>
        <name> {player2.name}</name>
        <cell> {player2.cell}</cell>
        <ticket> { player2.ticket}</ticket>
        <type> { player2.typ}</type>
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

