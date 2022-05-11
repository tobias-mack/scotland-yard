package de.htwg.se.ScotlandYard

import com.google.inject.name.Names
import com.google.inject.{AbstractModule, Provides, TypeLiteral}
import de.htwg.se.ScotlandYard.controller.{ControllerInterface, controllerBaseImpl}
import de.htwg.se.ScotlandYard.model.BoardInterface
import de.htwg.se.ScotlandYard.model.gameComponents.{Board, GameInformation, Player}
import net.codingwell.scalaguice.ScalaModule

class ScotlandYardModule extends AbstractModule :

  val default: Vector[Player] = Vector[Player]()
  val defaultGameInfo: GameInformation = GameInformation()

  override def configure(): Unit =
    bind(new TypeLiteral[Vector[Player]]() {}).annotatedWith(Names.named("DefaultPlayer")).toInstance(default)
    bind(classOf[GameInformation]).annotatedWith(Names.named("DefaultGameInfo")).toInstance(defaultGameInfo)
    bind(classOf[ControllerInterface]).to(classOf[controllerBaseImpl.Controller])
    bind(classOf[BoardInterface]).to(classOf[Board])
    //bind(classOf[FileIOInterface]).to(classOf[FileIOJSON]) //fileIO_XML_Impl.FileIOXMLLL
