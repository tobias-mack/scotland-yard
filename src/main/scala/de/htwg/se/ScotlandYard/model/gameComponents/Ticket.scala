package de.htwg.se.ScotlandYard.model.gameComponents

case class Ticket(taxi: Int = 0,
                  bus: Int = 0,
                  subway: Int = 0,
                  black: Int = 0):
  def isEmpty(): Boolean =
    taxi <= 0 && bus <= 0 && subway <= 0 && black <= 0

