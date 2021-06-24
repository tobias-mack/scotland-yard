
import de.htwg.se.ScotlandYard.controller.controllerBaseImpl.Controller
import de.htwg.se.ScotlandYard.model.fileIOComponent.fileIO_XML_Impl.FileIOXML
import de.htwg.se.ScotlandYard.model.gameComponents.Board


  val controller = new Controller
  controller.addDetective("Mr.X")
  controller.addDetective("Detective")

  val fileIOXML = new FileIOXML
  fileIOXML.save(controller)

