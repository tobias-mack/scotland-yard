package de.htwg.se.ScotlandYard.model.gameComponents


case class Cell (number: Int = 0) {

  @Override
  override def toString: String = {
    number.toString
  }

}