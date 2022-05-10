package fileIOComponent

import fileIOComponent.api.FileIOAPI
import scala.io.StdIn
import scala.util.{Failure, Success, Try}

object Rest {
  @main def run =
    Try(FileIOAPI) match
      case Success(_) => println("FileIO Rest Server is running!")
      case Failure(v) => println("FileIO Server couldn't be started! " + v.getMessage + v.getCause)

}
