package de.htwg.se.ScotlandYard.util

trait GameState

case class unknownCommandEvent() extends GameState {}
case class startEvent() extends GameState {}
case class nextPlayerEvent() extends GameState{}