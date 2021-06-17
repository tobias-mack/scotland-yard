package de.htwg.se.ScotlandYard.aview.GUI

import de.htwg.se.ScotlandYard.controller.ControllerInterface
import de.htwg.se.ScotlandYard.util.{Observer, UI}
import javafx.scene.layout.{BackgroundImage, BackgroundPosition, BackgroundRepeat, BackgroundSize}
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Pos
import scalafx.scene.Scene
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, Button, TextInputDialog}
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.{BorderPane, HBox, StackPane}
import scalafx.scene.paint.Color.{Black, Blue, Cyan, DodgerBlue, GhostWhite, Orange, YellowGreen}
import scalafx.scene.paint.{LinearGradient, Stops}
import scalafx.scene.shape.Circle
import scalafx.scene.text.Text

import scala.sys.exit

case class GUI(controller: ControllerInterface) extends UI with Observer with JFXApp {

  controller.add(this)
  val ButtonWidth = 100
  val ButtonHeight = 100
  val view = new ImageView(new Image("file:assets/Konstanz-Yard-Map.png"))
  val view2 = new ImageView(new Image("file:assets/boatTicket.jpg"))
  val circle: Circle = Circle(0, 0, 15, Blue)

  val mrx: Circle = Circle(0, 0, 15, Black)
  val player2: Circle = Circle(0, 0, 15, Blue)
  val player3: Circle = Circle(-90, -440, 15, GhostWhite)
  val player4: Circle = Circle(-110, -440, 15, Orange)
  val player5: Circle = Circle(-130, -440, 15, YellowGreen)
  val figures: Vector[Circle] = Vector[Circle](mrx, player2, player3, player4, player5)

  val menuTop: HBox = new HBox {
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
  val menuCenter: HBox = new HBox {
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

  val ButtonTwo: Button = new Button {
    tooltip = "two players will play this game"
    this.setMinWidth(ButtonWidth)
    this.setMinHeight(ButtonHeight)
    this.setBackground(new javafx.scene.layout.Background(img("number-2.png",
      ButtonWidth, ButtonHeight)))
    onMouseClicked = _ => {
      processInput("2")
      addPlayers(2)
      updateMenu()
    }
  }
  val ButtonThree: Button = new Button {
    tooltip = "three players will play this game"
    this.setMinWidth(ButtonWidth)
    this.setMinHeight(ButtonHeight)
    this.setBackground(new javafx.scene.layout.Background(img("number-3.png",
      ButtonWidth, ButtonHeight)))
    onMouseClicked = _ => {
      processInput("3")
      addPlayers(3)
      updateMenu()
    }
  }
  val ButtonFour: Button = new Button {
    tooltip = "four players will play this game"
    this.setMinWidth(ButtonWidth)
    this.setMinHeight(ButtonHeight)
    this.setBackground(new javafx.scene.layout.Background(img("number-4.png",
      ButtonWidth, ButtonHeight)))
    onMouseClicked = _ => {
      processInput("4")
      addPlayers(4)
      updateMenu()
    }
  }
  val ButtonFive: Button = new Button {
    tooltip = "five players will play this game"
    this.setMinWidth(ButtonWidth)
    this.setMinHeight(ButtonHeight)
    this.setBackground(new javafx.scene.layout.Background(img("number-5.png",
      ButtonWidth, ButtonHeight)))
    onMouseClicked = _ => {
      processInput("5")
      addPlayers(5)
      updateMenu()
    }
  }

  val TaxiButton: Button = new Button {
    tooltip = "Take the Taxi!"
    this.setMinWidth(ButtonWidth)
    this.setMinHeight(ButtonHeight)
    this.setBackground(new javafx.scene.layout.Background(img("taxiTicket.jpg",
      ButtonWidth, ButtonHeight)))
    onMouseClicked = _ => {
      processInput("taxi")
      val dialog = new TextInputDialog()
      dialog.title = "Taxi Ticket"
      dialog.headerText = getPlayName + " is at Location " + getPlayPos
      val ret = dialog.showAndWait()
      ret match {
        case Some(value) => processInput(value); moveFigure(StationLocater.findXYpos(value))
        case None => println("Wrong Input")
      }
      checkWin()
    }
  }
  val BusButton: Button = new Button {
    tooltip = "Take the Bus!"
    this.setMinWidth(ButtonWidth)
    this.setMinHeight(ButtonHeight)
    this.setBackground(new javafx.scene.layout.Background(img("busTicket.jpg",
      ButtonWidth, ButtonHeight)))
    onMouseClicked = _ => {
      processInput("bus")
      val dialog = new TextInputDialog()
      dialog.title = "Bus ticket"
      dialog.headerText = getPlayName + " is at Location " + getPlayPos
      val ret = dialog.showAndWait()
      ret match {
        case Some(value) => processInput(value); moveFigure(StationLocater.findXYpos(value))
        case None => println("Wrong Input")
      }
      checkWin()
    }
  }
  val SubButton: Button = new Button {
    tooltip = "Take the Subway!"
    this.setMinWidth(ButtonWidth)
    this.setMinHeight(ButtonHeight)
    this.setBackground(new javafx.scene.layout.Background(img("subTicket.jpg",
      ButtonWidth, ButtonHeight)))
    onMouseClicked = _ => {
      processInput("sub")
      val dialog = new TextInputDialog()
      dialog.title = "Subway"
      dialog.headerText = getPlayName + " is at Location " + getPlayPos
      val ret = dialog.showAndWait()
      ret match {
        case Some(value) => processInput(value); moveFigure(StationLocater.findXYpos(value))
        case None => println("Wrong Input")
      }
      checkWin()
    }
  }

  val map: HBox = new HBox {
    this.setBackground(new javafx.scene.layout.Background(img("Konstanz-Yard-Map.png",
      ButtonWidth, ButtonHeight)))
  }

  val menuMid: HBox = new HBox {
    val stackPane = new StackPane()
    stackPane.getChildren.addAll(view, mrx, player2, player3, player4, player5)
    figures.foreach(a => a.visible = false)
    initializeFigures()
    val root = new HBox()
    root.getChildren.add(stackPane)
    children.addAll(root)
  }
  val menuBottom: HBox = new HBox {
    alignment = Pos.Center
    children = List(ButtonTwo, ButtonThree, ButtonFour, ButtonFive)
    this.setSpacing(100)
  }

  val mapImg = new javafx.scene.layout.Background(img("Konstanz-Yard-Map.png",
    ButtonWidth, ButtonHeight))


  stage = new PrimaryStage {
    title = "Scotland Yard | Konstanz Edition"
    minWidth = 1412
    minHeight = 1017
    resizable = true
    scene = new Scene {
      stylesheets.add("style.css")
      root = new BorderPane {
        style = "-fx-border-color: #353535; -fx-background-color: #4d8ab0"
        top = menuTop
        center = menuMid
        bottom = menuBottom
      }
    }
  }

  def run(): Unit = {
    main(Array())
  }

  def updateMenu(): Unit = {
    menuBottom.children = List(TaxiButton, BusButton, SubButton)
  }

  def img(imgURL: String, width: Int, height: Int): BackgroundImage = {
    new BackgroundImage(new Image("/" + imgURL, width, height, false, true),
      BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
      BackgroundSize.DEFAULT)
  }

  def getPlayPos: Int = {
    controller.board.player(controller.order).cell.number
  }

  def getPlayName: String = {
    controller.board.player(controller.order).name
  }

  def addPlayers(n: Int): Unit = {
    for (n <- 1 to n) {
      val dialog = new TextInputDialog(defaultValue = "Player" + n) {
        initOwner(stage)
        title = "Welcome to Scotland Yard"
        headerText = "Type your name"
        contentText = "Player " + n + " please enter your name:"
      }
      val result = dialog.showAndWait()
      result match {
        case Some(value) => processInput(value); addFigure(controller.playerNumber)
        case None => println("Wrong Input")
      }
    }
  }

  def refresh(): Unit = {
  }

  override def processInput(input: String): Unit = {
    controller.exec(input)
  }

  def moveFigure(point: Option[Point]): Unit = {
    point match {
      case Some(point) => figures(controller.order).setTranslateX(point.x); figures(controller.order).setTranslateY(point.y)
      case None =>
    }
  }

  def addFigure(playerNr: Int): Unit = {
    playerNr match {
      case 2 => figures(0).visible = true; figures(1).visible = true
      case 3 => figures(0).visible = true; figures(1).visible = true; figures(2).visible = true
      case 4 => figures(0).visible = true; figures(1).visible = true; figures(2).visible = true; figures(3).visible = true
      case 5 => figures.foreach(a => a.visible = true)
    }
  }

  def initializeFigures(): Unit = {
    mrx.setTranslateX(330)
    mrx.setTranslateY(100)
    player2.setTranslateX(-70)
    player2.setTranslateY(-440)
    player3.setTranslateX(-90)
    player3.setTranslateY(-440)
    player4.setTranslateX(-110)
    player4.setTranslateY(-440)
    player5.setTranslateX(-130)
    player5.setTranslateY(-440)
  }

  def checkWin(): Unit = {
    if (controller.checkWinning()) {
      println("win")
      new Alert(AlertType.Information) {
        initOwner(stage)
        title = "WINNER WINNER CHICKEN DINNER"
        headerText = "You Won!"
        contentText = "You caught Mr. X!"
      }.showAndWait()
      exit()
    }
  }

  override def update(): Boolean = {
    refresh()
    true
  }
}
