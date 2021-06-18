package de.htwg.se.ScotlandYard.aview.GUI

import com.sun.javafx.scene.control.skin.Utils.getResource
import de.htwg.se.ScotlandYard.controller.ControllerInterface
import de.htwg.se.ScotlandYard.model.gameComponents.Player
import de.htwg.se.ScotlandYard.util.{Observer, UI}
import javafx.scene.layout.{BackgroundImage, BackgroundPosition, BackgroundRepeat, BackgroundSize}
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Pos
import scalafx.scene.Scene
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, Button, Label, TextArea, TextField, TextInputDialog}
import scalafx.scene.effect.{DropShadow, Glow, InnerShadow}
import scalafx.scene.control.{Alert, Button, TextInputDialog}
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.GridPane.{getColumnIndex, getRowIndex}
import scalafx.scene.layout._
import scalafx.scene.paint.Color.{Black, Blue, CadetBlue, Cyan, DarkGray, DarkMagenta, DarkRed, DodgerBlue, GhostWhite, Gray, LightGoldrenrodYellow, LightSalmon, LightSeaGreen, LightYellow, Orange, PaleGreen, Red, SeaGreen, White, Yellow, YellowGreen, color}
import scalafx.scene.paint.{Color, LinearGradient, Stops}
import scalafx.scene.shape.{Circle, Polygon, Rectangle}
import scalafx.scene.layout.{BorderPane, HBox, StackPane}
import scalafx.scene.paint.Color.{Black, Blue, Cyan, DodgerBlue, GhostWhite, Orange, YellowGreen}
import scalafx.scene.paint.{LinearGradient, Stops}
import scalafx.scene.shape.Circle
import scalafx.scene.text.Text
import javafx.geometry.Side
import javafx.scene.layout.{BackgroundImage, BackgroundPosition, BackgroundRepeat, BackgroundSize}
import javafx.animation.{Animation, ScaleTransition, SequentialTransition, TranslateTransition}
import javafx.scene.paint.ImagePattern
import scalafx.scene.text.Font
import javafx.util.Duration
import scalafx.application.Platform
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.image.Image
import scalafx.scene.layout.Background
import scalafx.scene.paint.Color
import scalafx.scene.text.Font.fontNames
import scalafx.stage.{Popup, PopupWindow}

import scala.sys.exit
// $COVERAGE-OFF$Disabling highlighting by default until a workaround for https://issues.scala-lang.org/browse/SI-8596 is found

case class GUI(controller: ControllerInterface) extends UI with Observer with JFXApp {

  controller.add(this)
  val ButtonWidth = 100
  val ButtonHeight = 100
  val view = new ImageView(new Image("file:assets/Konstanz-Yard-Map.png"))
  val view2 = new ImageView(new Image("file:assets/boatTicket.jpg"))
  val view3 = new ImageView(new Image("file:assets/MenuSelfmade.png"))
  val detectiveIcon = new Image("detectivePlayer.png")
  val detectivepattern = new ImagePattern(detectiveIcon)
  val mrXIcon = new Image("mrX.png")
  val mrXpattern = new ImagePattern(mrXIcon)

 // val circle: Circle = Circle(0,0,15,Blue)
  val mrx: Circle = Circle(0,0,32,Black); mrx.setFill(mrXpattern)
  val player2: Circle = Circle(0,0,32,Blue); player2.setFill(detectivepattern)
  val player3: Circle = Circle(-90,-440,32,GhostWhite); player3.setFill(detectivepattern)
  val player4: Circle = Circle(-110,-440,32,Orange); player4.setFill(detectivepattern)
  val player5: Circle = Circle(-130,-440,32,YellowGreen); player5.setFill(detectivepattern)
  val figures: Vector[Circle] =  Vector[Circle](mrx,player2,player3,player4,player5)

  lazy val ds = new DropShadow()
  ds.setOffsetY(3.0f)
  ds.setColor(Color.color(1.0f, 1.0f, 1.0f))

  val menuTop:HBox = new HBox{
/*
    padding = Insets(10)
    children = Seq(
      new Text {
        text = "Scotland Yard "
        font = Font("Verdana",20d)
        //style = "-fx-font:normal 100pt sans-serif";
        /*fill = new LinearGradient(
          endX = 0,
          stops = Stops(Black, DarkRed)
        )
        effect = new DropShadow {
          color = Gray
          radius = 25
          spread = 0.25
        }*/
      }
    )
*/
  }

  val ButtonTwo: Button = new Button {
    tooltip = "two players will play this game"
    this.setMinWidth(ButtonWidth)
    this.setMinHeight(ButtonHeight)
    this.setBackground(new javafx.scene.layout.Background(img("number-2.png",
                                                              ButtonWidth,ButtonHeight)))
      onMouseClicked = _ => {
        processInput("2")
        addPlayers(2)
        makeMapVisible()
        updateMenu()
      }
    }

  val ButtonThree: Button = new Button{
    tooltip = "three players will play this game"
    this.setMinWidth(ButtonWidth)
    this.setMinHeight(ButtonHeight)
    this.setBackground(new javafx.scene.layout.Background(img("number-3.png",
      ButtonWidth, ButtonHeight)))
    onMouseClicked = _ => {
      processInput("3")
      addPlayers(3)
      makeMapVisible()
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
      makeMapVisible()
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
      makeMapVisible()
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
      dialog.headerText = currentPlayerName + " is at Location " + currentPlayerPos
      val ret = dialog.showAndWait()
      ret match {
        case Some(value) => if(controller.checkPossDest(value.toInt,1) ) {anim(StationLocater.findXYpos(value))}; processInput(value);
        case None => println("Wrong Input")
      }
      checkWin()
      orderUpdate()
      updateMenu()
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
      dialog.headerText = currentPlayerName + " is at Location " + currentPlayerPos
      val ret = dialog.showAndWait()
      ret match {
        case Some(value) =>if(controller.checkPossDest(value.toInt,2) ) {anim(StationLocater.findXYpos(value))}; processInput(value);
        case None => println("Wrong Input")
      }
      checkWin()
      orderUpdate()
      updateMenu()
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
      dialog.headerText = currentPlayerName + " is at Location " + currentPlayerPos
      val ret = dialog.showAndWait()
      ret match {
        case Some(value) => if(controller.checkPossDest(value.toInt,3) ) {anim(StationLocater.findXYpos(value))}; processInput(value);
        case None => println("Wrong Input")
      }
      checkWin()
      orderUpdate()
      updateMenu()
    }
  }
  val BlackButton: Button = new Button {
    tooltip = "Take a black ticket!"
    this.setMinWidth(ButtonWidth)
    this.setMinHeight(ButtonHeight)
    this.setBackground(new javafx.scene.layout.Background(img("boatTicket.jpg",
      ButtonWidth,ButtonHeight)))
    onMouseClicked = _ => {
      processInput("black")
      val dialog = new TextInputDialog()
      dialog.title = "Black Ticket"
      dialog.headerText = currentPlayerName + " is at Location " + currentPlayerPos
      val ret = dialog.showAndWait()
      ret match {
        case Some(value) => if(controller.checkPossDest(value.toInt,4) ) {anim(StationLocater.findXYpos(value))}; processInput(value);
        case None => println("Wrong Input")
      }
      checkWin()
      orderUpdate()
      updateMenu()
    }
  }

  val map:HBox= new HBox{
    this.setBackground(new javafx.scene.layout.Background(img("Konstanz-Yard-Map.png",
      ButtonWidth,ButtonHeight)))
  }

  val menuMid: HBox = new HBox{
    alignment = Pos.Center
    val stackPane = new StackPane()
    stackPane.getChildren.addAll(view3,view, mrx,player2,player3,player4,player5)
    view.visible = false
    figures.foreach(a => a.visible = false)
    initializeFigures()
    children.addAll(stackPane)
  }
  val menuBottom:HBox = new HBox{
    alignment = Pos.Center
    spacing = 60
    children = List(ButtonTwo,ButtonThree,ButtonFour,ButtonFive)
  }

  val mapImg = new javafx.scene.layout.Background(img("Konstanz-Yard-Map.png",
    ButtonWidth,ButtonHeight))



  stage = new PrimaryStage {
    title = "Scotland Yard | Konstanz Edition"
    val imga = new Image("/detective.png")
    this.getIcons.add(imga)
    minWidth  = 1412
    minHeight = 1017
    resizable = true
    scene = new Scene {
      stylesheets.add( "style.css" )
      root = new BorderPane {
        style = "-fx-border-color: #353535; -fx-background-color: #333832"//#4d8ab0
        top = menuTop
        center = menuMid
        bottom = menuBottom
      }
    }
  }

  def run(): Unit = {
    main(Array())
  }
  def currentPlayer(): Player = {
    var order = controller.order
    if(order < 0) order = 0
    controller.board.player(order)
  }
  var currentOrder = 0
  def orderUpdate(): Unit = {
    this.currentOrder = (this.currentOrder + 1) % controller.board.player.size
  }
  def currentPlayerName():String = {
    controller.board.player(currentOrder).name
  }
  def currentPlayerPos:Int = {
    currentPlayer().cell.number
  }
  def currentPlayerTaxi():String = {
    controller.board.player(currentOrder).ticket.taxi.toString
  }
  def currentPlayerBus():String = {
    controller.board.player(currentOrder).ticket.bus.toString
  }
  def currentPlayerSub():String = {
    controller.board.player(currentOrder).ticket.subway.toString
  }
  def currentPlayerBlack():String = {
    controller.board.player(currentOrder).ticket.black.toString
  }
  def updateMenu():Unit ={

    val currentPlayer = new Text(currentPlayerName().toUpperCase())
    val currentTaxi = new Text(currentPlayerTaxi()+ " x")
    val currentBus = new Text(currentPlayerBus()+ " x")
    val currentSub = new Text(currentPlayerSub()+ " x")
    val currentBlack = new Text(currentPlayerBlack()+ " x")


    val textList =  List(currentPlayer,currentTaxi,currentBus,currentSub,currentBlack)

    for(n <- textList){
      n.setStyle("-fx-font: 25 Tahoma;")
      n.setFill(Yellow)
      n.setEffect(ds)
    }
    menuBottom.children.removeAll()
    if (currentOrder == 0){
      BlackButton.visible = true
      currentBlack.visible = true
      menuBottom.children = List(currentPlayer, currentTaxi, TaxiButton, currentBus,
                                  BusButton, currentSub, SubButton,currentBlack,BlackButton)
    }else {
      BlackButton.visible = false
      currentBlack.visible = false
      menuBottom.children = List(currentPlayer, currentTaxi, TaxiButton, currentBus,
                                  BusButton, currentSub, SubButton,currentBlack,BlackButton)
    }
  }
  def playerInf:String={
    val Statement = new StringBuilder()
    for (n <- controller.board.player){
      Statement.append(n.name + " has " + n.ticket.taxi + " Taxi tickets," + n.ticket.bus + " Bus tickets, " +
        n.ticket.subway + " Subway tickets" + "\n")
    }
    Statement.toString()
  }

  def img(imgURL: String, width: Int, height: Int): BackgroundImage = {
    new BackgroundImage(new Image("/" + imgURL, width, height, false, true),
      BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
      BackgroundSize.DEFAULT)
  }

  def makeMapVisible(): Unit = {
    addFigure(controller.playerNumber)
    view3.visible = false;view.visible=true
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
        case Some(value) => processInput(value) ;
        case None => println("Wrong Input")
      }
    }
  }
  def refresh():Unit = {
  }

  override def processInput(input: String): Unit = {
    controller.exec(input)
  }
  def moveFigure(point: Option[Point]):Unit={
    point match {
      case Some(point) =>
        figures(controller.order).setTranslateX(point.x)
        figures(controller.order).setTranslateY(point.y)
      case None =>
    }
  }

  def anim(point:Option[Point]):Unit={
    point match {
      case Some(point) =>
        val fig = figures(controller.order)
        val movingPath: TranslateTransition  = new TranslateTransition(Duration.millis(1000), fig)
        val currentPos = StationLocater.findXYpos(currentPlayerPos.toString).get
        val x = point.x - currentPos.x
        val y = point.y- currentPos.y
        movingPath.setCycleCount(1)
        movingPath.setAutoReverse(false)
        movingPath.setByX(x)
        movingPath.setByY(y)
        movingPath.play()
      case None => println("Wrong input. Number is not on map.")
    }
  }
  def addFigure(playerNr: Int):Unit = {
    playerNr match {
      case 2 => figures(0).visible = true; figures(1).visible = true;
      case 3 => figures(0).visible = true; figures(1).visible = true;figures(2).visible = true
      case 4 => figures(0).visible = true; figures(1).visible = true;figures(2).visible = true;figures(3).visible = true
      case 5 => figures.foreach(a => a.visible = true)
    }
  }
  def initializeFigures():Unit = {
    mrx.setTranslateX(330);  mrx.setTranslateY(100)
    //textMrX.setTranslateX(330); textMrX.setTranslateX(100);
    player2.setTranslateX(-70);  player2.setTranslateY(-440)
    player3.setTranslateX(-90);  player3.setTranslateY(-440)
    player4.setTranslateX(-110);  player4.setTranslateY(-440)
    player5.setTranslateX(-130);  player5.setTranslateY(-440)
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
// $COVERAGE-ON$