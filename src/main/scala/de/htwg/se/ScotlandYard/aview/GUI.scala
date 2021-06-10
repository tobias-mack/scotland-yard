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
import scalafx.stage.{Popup, PopupWindow}
case class GUI(controller: Controller) extends UI with Observer with JFXApp{
  controller.add(this)
  def run(): Unit = {
    main(Array())
  }
  val ButtonWidth = 100
  val ButtonHeight = 100

  val menuTop:HBox = new HBox{
    /*
    padding = Insets(10)
    children = Seq(
      new Text {
        text = "Scotland Yard "
        style = "-fx-font:normal 10pt sans-serif"
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
    */
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
  def getPlayPos():Int = {
    controller.board.player(controller.order).cell.number
  }
  def getPlayName():String ={
    controller.board.player(controller.order).name
  }

  def addPlayers(n: Int): Unit = {
    for(n <- 1 to n) {
      val dialog = new TextInputDialog(defaultValue = "Player" + n){
        initOwner(stage)
        title = "Welcome to Scotland Yard"
        headerText = "Type your name"
        contentText = "Player " + n + " please enter your name:"
      }
      val result = dialog.showAndWait()
      result match {
        case Some(value) => processInput(value)
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
        processInput("2")
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

  val TaxiButton: Button = new Button {
    tooltip = "Take the Taxi!"
    this.setMinWidth(ButtonWidth)
    this.setMinHeight(ButtonHeight)
    this.setBackground(new javafx.scene.layout.Background(img("taxiTicket.jpg",
      ButtonWidth,ButtonHeight)))
    onMouseClicked = _ => {
      processInput("taxi")
      val dialog = new TextInputDialog()
      dialog.title = "Taxi Ticket"
      dialog.headerText = getPlayName() + " is at Location " + getPlayPos()
      val ret = dialog.showAndWait()
      ret match {
        case Some(value) => processInput(value)
        case None => println("Wrong Input")
      }
    }
  }
  val BusButton: Button = new Button {
    tooltip = "Take the Bus!"
    this.setMinWidth(ButtonWidth)
    this.setMinHeight(ButtonHeight)
    this.setBackground(new javafx.scene.layout.Background(img("busTicket.jpg",
      ButtonWidth,ButtonHeight)))
    onMouseClicked = _ => {
      processInput("taxi")
      val dialog = new TextInputDialog()
      dialog.title = "Taxi"
      dialog.headerText = getPlayName() + " is at Location " + getPlayPos()
      val ret = dialog.showAndWait()
      ret match {
        case Some(value) => processInput(value)
        case None => println("Wrong Input")
      }
    }
  }

  val SubButton: Button = new Button {
    tooltip = "Take the Subway!"
    this.setMinWidth(ButtonWidth)
    this.setMinHeight(ButtonHeight)
    this.setBackground(new javafx.scene.layout.Background(img("subTicket.jpg",
      ButtonWidth,ButtonHeight)))
    onMouseClicked = _ => {
      processInput("taxi")
      val dialog = new TextInputDialog()
      dialog.title = "Taxi"
      dialog.headerText = getPlayName() + " is at Location " + getPlayPos()
      val ret = dialog.showAndWait()
      ret match {
        case Some(value) => processInput(value)
        case None => println("Wrong Input")
      }
    }
  }

  val menuBottom:HBox = new HBox{
    alignment = Pos.Center
    children = List(TaxiButton,BusButton,SubButton,ButtonTwo,ButtonThree,ButtonFour,ButtonFive)
    this.setSpacing(100)
  }
  val mapImg = new javafx.scene.layout.Background(img("Konstanz-Yard-Map.png",
    ButtonWidth,ButtonHeight))

  val img = new Image("file:assets/Konstanz-Yard-Map.png")
  val view = new ImageView(img)

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
        style = "-fx-border-color: #353535; -fx-background-color: #4d8ab0"
        top = menuTop
        center = view
        bottom = menuBottom
      }
    }
  }
    /*do {
      val playerSize = controller.players.size
      playerSize match {
        case 0 =>
        case 1 => typeName()
      }
    } while (controller.players.size<2)
    gameState.handle("")*/

  def refresh():Unit = {
  }
  override def processInput(input: String): Unit = {
    controller.exec(input)
  }
  override def update(): Boolean = {
    refresh()
    true
  }



}
