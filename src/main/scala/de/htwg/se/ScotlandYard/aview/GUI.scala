package de.htwg.se.ScotlandYard.aview

import de.htwg.se.ScotlandYard.controller.Controller
import de.htwg.se.ScotlandYard.util.{Observer, UI}
import org.scalactic.source.Position
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, Button, TextInputDialog}
import scalafx.scene.effect.{DropShadow, Glow, InnerShadow}
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.GridPane.{getColumnIndex, getRowIndex}
import scalafx.scene.layout._
import scalafx.scene.paint.Color.{BLACK, Black, Blue, CadetBlue, Cyan, DarkGray, DarkMagenta, DarkRed, DodgerBlue, LIGHTYELLOW, LightGoldrenrodYellow, LightSalmon, LightSeaGreen, LightYellow, PaleGreen, Red, SeaGreen, Yellow, YellowGreen}
import scalafx.scene.paint.{Color, LinearGradient, Stops}
import scalafx.scene.shape.Rectangle
import scalafx.scene.text.Text
import javafx.geometry.Side
import javafx.scene.layout.{BackgroundImage, BackgroundPosition, BackgroundRepeat, BackgroundSize}
import scalafx.application.Platform
import scalafx.scene.control.Alert
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.image.Image
import scalafx.scene.layout.Background
import scalafx.scene.paint.Color
case class GUI(controller: Controller) extends UI with Observer with JFXApp{
  controller.add(this)
  def run(): Unit = {
    main(Array())
  }



  val menuTop:HBox = new HBox{
    alignment = Pos.Center
    children = Seq(
      new Text {
        text = "Scotland Yard "
        style = "-fx-font:normal 100pt sans-serif"
        fill = new LinearGradient(
          endX = 0,
          stops = Stops(Blue, CadetBlue)
        )
        effect = new DropShadow {
          color = Yellow
          radius = 25
          spread = 0.25
        }
      }
    )
  }
  val menuCenter:HBox = new HBox{
    alignment = Pos.Center
    children = Seq(
      new Text {
        text = "MENU"
        style = "-fx-font:normal 48pt sans-serif"
        fill = new LinearGradient(
          endX = 0,
          stops = Stops(Cyan, DodgerBlue)
        )
      }
    )
  }
  def img(imgURL:String,width:Int,height:Int):BackgroundImage = {
    new BackgroundImage(new Image("/"+imgURL,width,height,false,true),
      BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
      BackgroundSize.DEFAULT)
  }
  val ButtonWidth = 100
  val ButtonHeight = 100
  def addPlayers(n: Int): Unit = {
    for(n <- 1 to n) {
      val dialog = new TextInputDialog(defaultValue = "Mr. X"){
        initOwner(stage)
        title = "Welcome to Scotland Yard"
        headerText = "Type your name"
        contentText = "Player " + n + " please enter your name:"
      }
      val result = dialog.showAndWait()
      result match {
        case Some(value) => controller.addDetective(value)
        case None => println("Wrong Input")
      }
    }
  }
  val ButtonTwo: Button = new Button{
    tooltip = "two players will play this game"
    this.setMinWidth(ButtonWidth)
    this.setMinHeight(ButtonHeight)
    this.setBackground(new javafx.scene.layout.Background(img("number-2.png",
                                                              ButtonWidth,ButtonHeight)))
      onMouseClicked = _ => {
        addPlayers(2)

      }
    }

  val ButtonThree: Button = new Button{
    tooltip = "three players will play this game"
    this.setMinWidth(ButtonWidth)
    this.setMinHeight(ButtonHeight)
    this.setBackground(new javafx.scene.layout.Background(img("number-3.png",
      ButtonWidth,ButtonHeight)))
    onMouseClicked = _ => {
      addPlayers(3)
    }
  }
  val ButtonFour: Button = new Button{
    tooltip = "four players will play this game"
    this.setMinWidth(ButtonWidth)
    this.setMinHeight(ButtonHeight)
    this.setBackground(new javafx.scene.layout.Background(img("number-4.png",
      ButtonWidth,ButtonHeight)))
    onMouseClicked = _ => {
      addPlayers(4)
    }
  }
  val map:HBox= new HBox{
    this.setBackground(new javafx.scene.layout.Background(img("Konstanz-Yard-Map.png",
      ButtonWidth,ButtonHeight)))
  }
  val ButtonFive: Button = new Button{
    tooltip = "five players will play this game"
    this.setMinWidth(ButtonWidth)
    this.setMinHeight(ButtonHeight)
    this.setBackground(new javafx.scene.layout.Background(img("number-5.png",
      ButtonWidth,ButtonHeight)))
    onMouseClicked = _ => {
      addPlayers(5)
    }
  }
  val menuBottom:HBox = new HBox{
    alignment = Pos.Center
    children = List(ButtonTwo,ButtonThree,ButtonFour,ButtonFive)
    this.setSpacing(100)
  }
  stage = new PrimaryStage {
    title = "Scotland Yard | Konstanz Edition"
    minWidth  = 1412
   // maxWidth = minWidth
    minHeight = 1017
    //maxHeight = minHeight
    resizable = true
    scene = new Scene {
      stylesheets.add( "style.css" )
      root = new BorderPane {
        style = "-fx-border-color: #353535; -fx-background-color: #e3e3e3"
        top = menuTop
        center = menuCenter
        bottom = menuBottom
      }
      begin()
    }
  }
  def begin():Unit = {
    typeName()
    typeName()
    /*do {
      val playerSize = controller.players.size
      playerSize match {
        case 0 =>
        case 1 => typeName()
      }
    } while (controller.players.size<2)
    gameState.handle("")*/
  }
  def typeName():Unit = {
    val dialog = new TextInputDialog(defaultValue = "Detlef") {
      initOwner(stage)
      title = "Scotland Yard | Start Screen"
      headerText = "Welcome to Connect Four!"
      var number = ""
      //if (controller.players.size == 0) number = "one" else number = "two"
      contentText = "Player " + number + " please enter your name:"
    }
  }
  def refresh():Unit = {
  }
  override def processInput(input: String): Unit = {
  }
  override def update(): Boolean = {
    refresh()
    true
  }



}
