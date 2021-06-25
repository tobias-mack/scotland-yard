name          := "ScotlandYard"
organization  := "de.htwg.se"
version       := "0.0.1"
scalaVersion  := "2.13.5"


libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.7"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.7" % "test"
libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.4"
libraryDependencies += "org.scala-graph" %% "graph-core" % "1.13.2"
libraryDependencies += "org.apache.commons" % "commons-io" % "1.3.2"
libraryDependencies += "org.scalafx" %% "scalafx" % "15.0.1-R21"
libraryDependencies ++= javaFXModules

libraryDependencies += "com.google.inject" % "guice" % "5.0.1"
libraryDependencies += "net.codingwell" %% "scala-guice" % "5.0.1"

libraryDependencies += "org.scala-lang.modules" % "scala-xml_2.13" % "2.0.0"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.10.0-RC2"

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

coverageExcludedPackages := "de.htwg.se.ScotlandYard.aview.*;" +
  "de.htwg.se.ScotlandYard.model.fileIOComponent.*;"
