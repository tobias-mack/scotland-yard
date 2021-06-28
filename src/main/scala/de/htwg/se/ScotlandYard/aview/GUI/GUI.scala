package de.htwg.se.ScotlandYard.aview.GUI

import de.htwg.se.ScotlandYard.controller.ControllerInterface
import de.htwg.se.ScotlandYard.controller.controllerBaseImpl.GameState
import de.htwg.se.ScotlandYard.model.gameComponents.Player
import de.htwg.se.ScotlandYard.util.{Observer, State, UI}
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.effect.DropShadow
import scalafx.scene.control.{Alert, Button, TextInputDialog}
import scalafx.scene.image.ImageView
import scalafx.scene.paint.Color.{Black, Blue, DarkBlue, GhostWhite, LightSkyBlue, Orange, OrangeRed, Red, Yellow, YellowGreen}
import scalafx.scene.layout.{BorderPane, HBox, StackPane, VBox}
import scalafx.scene.shape.Circle
import scalafx.scene.text.Text
import javafx.scene.layout.{Background, BackgroundImage, BackgroundPosition, BackgroundRepeat, BackgroundSize}
import javafx.animation.{RotateTransition, TranslateTransition}
import javafx.scene.paint.ImagePattern
import javafx.util.Duration
import scalafx.application.Platform
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.image.Image
import scalafx.scene.paint.Color
import javafx.geometry.Rectangle2D
import javafx.stage.Screen

import scala.util.{Failure, Success, Try}

case class GUI(controller: ControllerInterface) extends UI with Observer with JFXApp {

  controller.add(this)
  var roundCounter:Int = 1
  val primaryScreenBounds: Rectangle2D = Screen.getPrimary.getVisualBounds
  val ButtonWidth:Int = 90
  val ButtonHeight:Int = 90

  val windowWidth:Int = primaryScreenBounds.getWidth.toInt
  val windowHeight:Int = primaryScreenBounds.getHeight.toInt - (ButtonHeight*1.5).toInt
  val mapImg: Image = new Image("file:assets/Konstanz-Yard-Map-withConnections.png",windowWidth,windowHeight,true,false)
  val mapWidth: Double = mapImg.getWidth
  val mapHeight: Double= mapImg.getHeight
  val mapfactor: Double = mapWidth/1412
  val mapfactor2:Double = mapHeight/1017

  val view = new ImageView(mapImg)
  val view3 = new ImageView(new Image("file:assets/MenuSelfmade.png",windowWidth,windowHeight,true,false))
  val detectiveIcon = new Image("detectivePlayer.png")
  val detectivepattern = new ImagePattern(detectiveIcon)
  val mrXIcon = new Image("mrX.png")
  val mrXpattern = new ImagePattern(mrXIcon)
  val arrowIcon = new Image("simpleArrow.png")
  val arrowpattern = new ImagePattern(arrowIcon)

  val arrow: Circle = Circle(0,0,32,Blue); arrow.setFill(arrowpattern)
  val mrx: Circle = Circle(0,0,32,Black); mrx.setFill(mrXpattern)
  val player2: Circle = Circle(0,0,32,Blue); player2.setFill(detectivepattern)
  val player3: Circle = Circle(0,0,32,GhostWhite); player3.setFill(detectivepattern)
  val player4: Circle = Circle(0,0,32,Orange); player4.setFill(detectivepattern)
  val player5: Circle = Circle(0,0,32,YellowGreen); player5.setFill(detectivepattern)
  val figures: Vector[Circle] =  Vector[Circle](mrx,player2,player3,player4,player5)

  lazy val ds = new DropShadow()
  ds.setOffsetY(3.0f)
  ds.setColor(Color.color(OrangeRed.red,OrangeRed.green,OrangeRed.blue))

  val ButtonSave: Button = new Button {
    tooltip = "save this game"
    this.setMinWidth(ButtonWidth)
    this.setMinHeight(ButtonHeight)
    this.setBackground(new javafx.scene.layout.Background(img("primary-save.png",
      ButtonWidth,ButtonHeight)))
    onMouseClicked = _ => {
      controller.save()
      new Alert(AlertType.Information) {
        initOwner(stage)
        title = "Saving-Information"
        headerText = "You saved this game"
        contentText = "bye"
      }.showAndWait()
    }
  }

  val ButtonLoad: Button = new Button {
    tooltip = "load the previous save"
    this.setMinWidth(ButtonWidth)
    this.setMinHeight(ButtonHeight)
    this.setBackground(new javafx.scene.layout.Background(img("reload-icon.png",
      ButtonWidth,ButtonHeight)))
    onMouseClicked = _ => {
      controller.load()
      updateAfterLoad()
      new Alert(AlertType.Information) {
        initOwner(stage)
        title = "Loading-Information"
        headerText = "You loaded an old save file"
        contentText = "bye"
      }.showAndWait()
    }
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
      handleInput(1)
    }
  }
  val BusButton: Button = new Button {
    tooltip = "Take the Bus!"
    this.setMinWidth(ButtonWidth)
    this.setMinHeight(ButtonHeight)
    this.setBackground(new javafx.scene.layout.Background(img("busTicket.jpg",
      ButtonWidth, ButtonHeight)))
    onMouseClicked = _ => {
      handleInput(2)
    }
  }
  val SubButton: Button = new Button {
    tooltip = "Take the Subway!"
    this.setMinWidth(ButtonWidth)
    this.setMinHeight(ButtonHeight)
    this.setBackground(new javafx.scene.layout.Background(img("subTicket.jpg",
      ButtonWidth, ButtonHeight)))
    onMouseClicked = _ => {
      handleInput(3)
    }
  }
  val BlackButton: Button = new Button {
    tooltip = "Take a black ticket!"
    this.setMinWidth(ButtonWidth)
    this.setMinHeight(ButtonHeight)
    this.setBackground(new javafx.scene.layout.Background(img("boatTicket.jpg",
      ButtonWidth,ButtonHeight)))
    onMouseClicked = _ => {
      handleInput(4)
    }
  }

  val buttonLog1 : Button = new Button {
    tooltip = "first ticket that Mr.X has used since last position reveal!"
    this.setMinWidth(ButtonWidth)
    this.setMinHeight(ButtonHeight)
    this.setBackground(new Background(img("Log1.png",
      ButtonWidth,ButtonHeight)))
  }
  val buttonLog2 : Button = new Button {
    tooltip = "second ticket that Mr.X has used since last position reveal!"
    this.setMinWidth(ButtonWidth)
    this.setMinHeight(ButtonHeight)
    this.setBackground(new Background(img("Log2.png",
      ButtonWidth,ButtonHeight)))
  }
  val buttonLog3 : Button = new Button {
    tooltip = "third ticket that Mr.X has used since last position reveal!"
    this.setMinWidth(ButtonWidth)
    this.setMinHeight(ButtonHeight)
    this.setBackground(new Background(img("Log3.png",
      ButtonWidth,ButtonHeight)))
  }

  val menuMid: HBox = new HBox{
    alignment = Pos.Center
    val stackPane = new StackPane()
    stackPane.getChildren.addAll(view3,view,arrow, mrx,player2,player3,player4,player5)
    view.visible = false
    arrow.visible = false
    figures.foreach(a => a.visible = false)
    initializeFigures()
    children.addAll(stackPane)
  }
  val menuBottom:HBox = new HBox{
    alignment = Pos.Center

    spacing = 60 * mapfactor
    children = List(ButtonTwo,ButtonThree,ButtonFour,ButtonFive)
  }
  val travelLog:VBox = new VBox{
    alignment = Pos.Center
  }
  val menuSaveLoad:VBox = new VBox{
    alignment = Pos.Center
  }

  stage = new PrimaryStage {
    title = "Scotland Yard | Konstanz Edition"
    this.setMaximized(true)
    val imga = new Image("/detective.png")
    this.getIcons.add(imga)
    resizable = false

    scene = new Scene {
      root = new BorderPane {
        style = "-fx-border-color: #353535; -fx-background-color: #4d8ab0"//#333832
        left = menuSaveLoad
        right = travelLog
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

  def currentPlayerName():String = {
    controller.board.player(controller.order).name
  }
  def currentPlayerPos:Int = {
    currentPlayer().cell.number
  }
  def currentPlayerTaxi():String = {
    controller.board.player(controller.order).ticket.taxi.toString
  }
  def currentPlayerBus():String = {
    controller.board.player(controller.order).ticket.bus.toString
  }
  def currentPlayerSub():String = {
    controller.board.player(controller.order).ticket.subway.toString
  }
  def currentPlayerBlack():String = {
    controller.board.player(controller.order).ticket.black.toString
  }
  def updateAfterLoad():Unit ={
    val PosMrX = StationLocater.findXYpos(controller.board.player(0).cell.number.toString).get
    val PosPl = StationLocater.findXYpos(controller.board.player(1).cell.number.toString).get

    updateMenu()
    updateArrow()

    mrx.setTranslateX(PosMrX.x * mapfactor); mrx.setTranslateY(PosMrX.y * mapfactor)
    player2.setTranslateX(PosPl.x * mapfactor); player2.setTranslateY(PosPl.y * mapfactor)

    resetTravelLog()
    controller.travelLog.size match{
      case 1 =>
        updateLog(controller.travelLog.head,2)
      case 2 =>
        updateLog(controller.travelLog.head,2)
        updateLog(controller.travelLog(1),1)
      case 3 =>
        updateLog(controller.travelLog.head,2)
        updateLog(controller.travelLog(1),1)
        updateLog(controller.travelLog(2),0)
    }
  }

  def updateLog(transport:Int, revealCounter: Int): Unit ={
    transport match{
      case 1 => updateLog("taxi",revealCounter)
      case 2 => updateLog("bus",revealCounter)
      case 3 => updateLog("sub",revealCounter)
      case 4 => updateLog("black", revealCounter)
    }
  }
  def updateMenu():Unit ={
    val round = new Text("Round: " + roundCounter)
    val currentPlayer = new Text(currentPlayerName().toUpperCase())
    val currentTaxi = new Text(currentPlayerTaxi()+ " x")
    val currentBus = new Text(currentPlayerBus()+ " x")
    val currentSub = new Text(currentPlayerSub()+ " x")
    val currentBlack = new Text(currentPlayerBlack()+ " x")
    val textList =  List(round,currentPlayer,currentTaxi,currentBus,currentSub,currentBlack)

    for(n <- textList){
      n.setStyle("-fx-font: 25 Tahoma;")
      n.setEffect(ds)
    }
    round.setFill(Orange)
    currentPlayer.setFill(Yellow)
    currentTaxi.setFill(Yellow)
    currentBus.setFill(LightSkyBlue)
    currentSub.setFill(Red)
    currentBlack.setFill(DarkBlue)

    menuBottom.children.removeAll()
    menuBottom.setAlignment(Pos.CenterLeft)
    menuBottom.padding = Insets(0,0,0,200*mapfactor)

    if (controller.order == 0){
      BlackButton.visible = true
      currentBlack.visible = true
      menuBottom.children = List(round,currentPlayer, currentTaxi, TaxiButton, currentBus,
                                  BusButton, currentSub, SubButton,currentBlack,BlackButton)
    }else {
      BlackButton.visible = false
      currentBlack.visible = false
      menuBottom.children = List(round,currentPlayer, currentTaxi, TaxiButton, currentBus,
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
    val InfoText = new Text("Mister X Travel-Log\n")
    InfoText.setStyle("-fx-font: 40 Tahoma;")
    InfoText.setEffect(ds)
    InfoText.setFill(Black)
    travelLog.children = List(InfoText,buttonLog1,buttonLog2,buttonLog3)
    menuSaveLoad.children = List(ButtonSave,ButtonLoad)
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

  override def processInput(input: String): State[GameState] = {
    controller.exec(input)
  }

  def moveArrow (point: Option[Point]):Unit={
    point match {
      case Some(point) =>
        arrow.setTranslateX(point.x * mapfactor)
        arrow.setTranslateY((point.y  - 70)* mapfactor)
        if(controller.order==0 && controller.revealCounter == 0){
          mrx.setTranslateX(point.x * mapfactor); mrx.setTranslateY(point.y * mapfactor)
          resetTravelLog()
        }
      case None =>
    }
  }

  def resetTravelLog():Unit={
    buttonLog1.setBackground(new Background(img("Log1.png", ButtonWidth,ButtonHeight)))
    buttonLog2.setBackground(new Background(img("Log2.png", ButtonWidth,ButtonHeight)))
    buttonLog3.setBackground(new Background(img("Log3.png", ButtonWidth,ButtonHeight)))
  }

  def handleInput(transport:Int):Boolean = {
    var transportString = "taxi"
    if(controller.checkTransport(transport,controller.order)){
      transport match{
        case 1 =>
        case 2 => transportString = "bus"
        case 3 => transportString = "sub"
        case 4 => transportString = "black"
      }
      processInput(transportString)

      val dialog = new TextInputDialog()
      dialog.title = s"${transportString.toUpperCase} - Ticket"
      dialog.headerText = currentPlayerName + " is at Location " + currentPlayerPos
      var inputCorrect: Boolean = false
      do{
        val ret = dialog.showAndWait()
        ret match {
          case Some(value) =>
            val position: Try[Int] = controller.posToInt(value)
            position match {
              case Success(pos) =>
                if(controller.order==0 ) {
                  if (controller.checkPossDest(pos, transport)) {
                    processInput(value)
                    updateLog(transportString,controller.revealCounter)
                    updateArrow()
                    inputCorrect = true
                  }
                }
                else{
                  if (controller.checkPossDest(pos, transport)) {
                    anim(StationLocater.findXYpos(value))
                    processInput(value)
                    inputCorrect = true
                  }
                }
              case Failure(_) => println(s"Moving to position $value not possible")
            }
          case None => println("Wrong Input. Pls type in a number.")
        }
      }while(!inputCorrect)
      checkWin()
      checkLoosing()
      updateRound()
      updateMenu()
      updateArrow()
      true
    }
    else{
      new Alert(AlertType.Information) {
        initOwner(stage)
        title = "Ticket-Information"
        headerText = "You cannot use this ticket at your Location"
        contentText = "Try another ticket"
      }.showAndWait()
      false
    }
  }

  def updateReveal():Unit={
    this.controller.revealCounter = controller.revealCounter
  }

  def updateLog(transport:String,revealCounter:Int):Unit={
    var imgTransport:BackgroundImage = img("taxiTicket.jpg", ButtonWidth, ButtonHeight)
      transport match {
        case "taxi" => imgTransport = img("taxiTicket.jpg", ButtonWidth, ButtonHeight)
        case "bus" => imgTransport = img("busTicket.jpg", ButtonWidth, ButtonHeight)
        case "sub"=> imgTransport = img("subTicket.jpg", ButtonWidth, ButtonHeight)
        case "black" =>  imgTransport = img("boatTicket.jpg", ButtonWidth, ButtonHeight)
      }
      revealCounter match {
        case 2 => buttonLog1.setBackground(new javafx.scene.layout.Background(imgTransport))
        case 1 => buttonLog2.setBackground(new javafx.scene.layout.Background(imgTransport))
        case 0 => buttonLog3.setBackground(new javafx.scene.layout.Background(imgTransport))
      }
  }
  def updateArrow(): Unit = {
    if(this.controller.order == 0 && controller.revealCounter != 0) {arrow.visible = false}
    else{
      arrow.visible = true
      val currentPos = StationLocater.findXYpos(controller.board.player(controller.order).cell.number.toString)
      moveArrow(currentPos)

      val rotate = new RotateTransition(Duration.seconds(1), arrow)
      rotate.setByAngle(360)
      rotate.setDelay(Duration.seconds(1))
      rotate.setRate(10)
      rotate.setCycleCount(10)
      rotate.play()

      val movingPath: TranslateTransition = new TranslateTransition(Duration.millis(500), arrow)
      val x = 0
      val y = 10
      movingPath.setCycleCount(10)
      movingPath.setAutoReverse(true)
      movingPath.setByX(x)
      movingPath.setByY(y)
      movingPath.play()
    }
  }

  def updateRound():Unit={
    if(controller.order == 0) {
      roundCounter += 1
    }
  }
  def anim(point:Option[Point]):Unit={
    point match {
      case Some(point) =>
        val fig = figures(controller.order)
        val movingPath: TranslateTransition  = new TranslateTransition(Duration.millis(1000), fig)
        val currentPos = StationLocater.findXYpos(currentPlayerPos.toString).get
        val x = point.x * mapfactor - currentPos.x * mapfactor
        val y = point.y * mapfactor - currentPos.y * mapfactor
        movingPath.setCycleCount(1)
        movingPath.setAutoReverse(false)
        movingPath.setByX(x)
        movingPath.setByY(y)
        movingPath.play()
      case None => println("Wrong input. Number is not on map.")
    }
  }
  def addFigure(playerNr: Int):Unit = {
    arrow.visible = true
    figures(0).visible = true; figures(1).visible = true
    playerNr match {
      case 2 =>
      case 3 => figures(2).visible = true
      case 4 => figures(2).visible = true; figures(3).visible = true
      case 5 => figures.foreach(a => a.visible = true)
    }
  }
  def initializeFigures():Unit = {
    val startPosMrX = StationLocater.findXYpos("5").get
    val startPosPl = StationLocater.findXYpos("1").get

    arrow.setTranslateX(   startPosMrX.x    *mapfactor); arrow.setTranslateY((startPosMrX.y - 60) * mapfactor)
    mrx.setTranslateX(     startPosMrX.x    *mapfactor); mrx.setTranslateY(startPosMrX.y*mapfactor)
    player2.setTranslateX( startPosPl.x     *mapfactor); player2.setTranslateY(startPosPl.y * mapfactor)
    player3.setTranslateX((startPosPl.x-20) *mapfactor); player3.setTranslateY(startPosPl.y * mapfactor)
    player4.setTranslateX((startPosPl.x-40) *mapfactor); player4.setTranslateY(startPosPl.y * mapfactor)
    player5.setTranslateX((startPosPl.x-60) *mapfactor); player5.setTranslateY(startPosPl.y * mapfactor)
  }

  def checkWin(): Unit = {
    if (controller.checkWinning()) {
      new Alert(AlertType.Information) {
        initOwner(stage)
        title = "WINNER WINNER CHICKEN DINNER"
        headerText = "Detectives Win!"
        contentText = "You caught Mr. X!"
      }.showAndWait()
      Platform.exit()
    }
  }
  def checkLoosing(): Unit = {
    if (controller.checkLoosing()) {
      new Alert(AlertType.Information) {
        initOwner(stage)
        title = "Detectives have lost"
        headerText = "You have no more tickets to move"
        contentText = "Mr.X wins"
      }.showAndWait()
      Platform.exit()
    }
  }
  override def update(): Boolean = {
    println(controller)
    true
  }
}