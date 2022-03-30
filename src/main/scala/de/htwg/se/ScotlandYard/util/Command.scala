package de.htwg.se.ScotlandYard.util

trait Command:
  def doStep(): Unit

  def undoStep(): Unit

  def redoStep(): Unit
