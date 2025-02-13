package de.htwg.se.ScotlandYard

import com.google.inject.name.Names
import com.google.inject.{AbstractModule, Provides, TypeLiteral}
import de.htwg.se.ScotlandYard.controller.{ControllerInterface, controllerBaseImpl}
import fileIOComponent.FileIOInterface
import fileIOComponent.fileIO_JSON_Impl.FileIOJSON
import modell.gameComponents.{Board, Player, GameInformation}
import modell.{BoardInterface, gameComponents}
import net.codingwell.scalaguice.ScalaModule

class ScotlandYardModule extends AbstractModule :

  val default: Vector[Player] = Vector[Player]()
  val defaultGameInfo: GameInformation = GameInformation()

  override def configure(): Unit =
    bind(new TypeLiteral[Vector[Player]]() {}).annotatedWith(Names.named("DefaultPlayer")).toInstance(default)
    bind(classOf[GameInformation]).annotatedWith(Names.named("DefaultGameInfo")).toInstance(defaultGameInfo)
    bind(classOf[ControllerInterface]).to(classOf[controllerBaseImpl.Controller])
    bind(classOf[BoardInterface]).to(classOf[Board])
    bind(classOf[FileIOInterface]).to(classOf[FileIOJSON]) //fileIO_XML_Impl.FileIOXMLLL
