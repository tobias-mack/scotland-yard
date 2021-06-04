package de.htwg.se.ScotlandYard.aview.GUI

import scala.swing.{
  SimpleSwingApplication,
  MainFrame,
  Dimension,
  Label,
  Font
}

import java.awt.Font.BOLD

object Hello extends SimpleSwingApplication {
  def top = new MainFrame {
    title = "Hello"
    preferredSize = new Dimension(300, 200)

    contents = new Label {
      text = "Hello!"
      font = new Font("Arial", BOLD, 24)
      resizable = false
    }

    peer.setLocationRelativeTo(null)
  }
}