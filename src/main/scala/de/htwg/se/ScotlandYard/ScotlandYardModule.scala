package de.htwg.se.ScotlandYard

import com.google.inject.{AbstractModule, Provides, TypeLiteral}
import com.google.inject.name.Names
import de.htwg.se.ScotlandYard.controller.ControllerInterface
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.ScotlandYard.controller.controllerBaseImpl
import de.htwg.se.ScotlandYard.model.BoardInterface
import de.htwg.se.ScotlandYard.model.fileIOComponent.FileIOInterface
import de.htwg.se.ScotlandYard.model.gameComponents
import de.htwg.se.ScotlandYard.model.gameComponents.Player
import de.htwg.se.ScotlandYard.model.fileIOComponent.fileIO_XML_Impl
import de.htwg.se.ScotlandYard.model.fileIOComponent.fileIO_JSON_Impl

class ScotlandYardModule extends AbstractModule {

  val default: Vector[Player] = Vector[Player]()

  override def configure():Unit = {
    bind(new TypeLiteral[Vector[Player]]() {}).annotatedWith(Names.named("DefaultPlayer")).toInstance(default)
    bind(classOf[ControllerInterface]).to(classOf[controllerBaseImpl.Controller])
    bind(classOf[BoardInterface]).to(classOf[gameComponents.Board])
    bind(classOf[FileIOInterface]).to(classOf[fileIO_JSON_Impl.FileIOJSON]) //fileIO_XML_Impl.FileIOXML
  }
}
