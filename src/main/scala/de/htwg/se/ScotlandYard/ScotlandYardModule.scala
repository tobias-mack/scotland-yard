package de.htwg.se.ScotlandYard

import com.google.inject.name.Names
import com.google.inject.AbstractModule
import de.htwg.se.ScotlandYard.controller.ControllerInterface
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.ScotlandYard.controller.controllerBaseImpl
import de.htwg.se.ScotlandYard.model.BoardInterface
import de.htwg.se.ScotlandYard.model.gameComponents
import de.htwg.se.ScotlandYard.model.gameComponents.Cell


class ScotlandYardModule extends AbstractModule with ScalaModule {

  override def configure():Unit = {
    bind[ControllerInterface].to[controllerBaseImpl.Controller]
    bind[BoardInterface].to[gameComponents.Board]

  }
}
