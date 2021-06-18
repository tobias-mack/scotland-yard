name          := "ScotlandYard"
organization  := "de.htwg.se"
version       := "0.0.1"
scalaVersion  := "2.13.5"

//libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"

//libraryDependencies += "junit" % "junit" % "4.8" % "test"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.7"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.7" % "test"
libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.4"
libraryDependencies += "org.scala-graph" %% "graph-core" % "1.13.2"
libraryDependencies += "org.apache.commons" % "commons-io" % "1.3.2"
libraryDependencies += "org.scalafx" %% "scalafx" % "15.0.1-R21"
libraryDependencies ++= javaFXModules
libraryDependencies += "com.google.inject" % "guice" % "5.0.1"
libraryDependencies += "net.codingwell" %% "scala-guice" % "5.0.1"

lazy val javaFXModules = {
  // Determine OS version of JavaFX binaries
  lazy val osName = System.getProperty("os.name") match {
    case n if n.startsWith("Linux")   => "linux"
    case n if n.startsWith("Mac")     => "mac"
    case n if n.startsWith("Windows") => "win"
    case _                            =>
      throw new Exception("Unknown platform!")
  }
  // Create dependencies for JavaFX modules
  Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
    .map( m=> "org.openjfx" % s"javafx-$m" % "15.0.1" classifier osName)
}

scalacOptions += "-deprecation"
scalacOptions += "-feature"
scalacOptions +=  "-language:reflectiveCalls"

coverageExcludedPackages := "de.htwg.se.ScotlandYard.aview.*"


//*******************************************************************************//
//Libraries that we will use in later lectures compatible with this scala version
// uncomment to use!!

//libraryDependencies += "org.scala-lang.modules" % "scala-swing_2.12" % "2.0.1"

//libraryDependencies += "com.google.inject" % "guice" % "4.1.0"

//libraryDependencies += "net.codingwell" %% "scala-guice" % "4.1.0"

//libraryDependencies += "org.scala-lang.modules" % "scala-xml_2.12" % "1.0.6"

//libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.6"
