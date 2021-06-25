package de.htwg.se.ScotlandYard.model.gameComponents


case class Cell (number: Int = 0) {

  @Override
  override def toString: String = {
    number.toString
  }

  object Cell {
    import play.api.libs.json._
    implicit val cellWrites: OWrites[Cell] = Json.writes[Cell]
    implicit val cellReads: Reads[Cell] = Json.reads[Cell]
  }

}