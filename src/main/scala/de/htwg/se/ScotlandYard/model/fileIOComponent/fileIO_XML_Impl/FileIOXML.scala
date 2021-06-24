
package de.htwg.se.ScotlandYard.model.fileIOComponent.fileIO_XML_Impl

import com.google.inject.Guice
import com.google.inject.name.Names
import de.htwg.se.ScotlandYard.ScotlandYard.controller.board
import de.htwg.se.ScotlandYard.model.BoardInterface
import de.htwg.se.ScotlandYard.model.fileIOComponent.FileIOInterface
import net.codingwell.scalaguice.InjectorExtensions._


class FileIOXML extends FileIOInterface{




  override def load: BoardInterface = ???


  def saveString(board: BoardInterface): Unit = {

  }

  override def save(board: BoardInterface): Unit = {
    saveString(board)
  }


  def saveXML(Board: BoardInterface): Unit = {
    scala.xml.XML.save("board.xml", boardToXML(board))
  }


  def boardToXML(board: BoardInterface): Unit = {
    <board player1={board.player.toString()}></board>
  }

  }

  def cellToXML(board: BoardInterface): Unit = {
}

