name          := "ScotlandYard"
organization  := "de.htwg.se"
version       := "0.0.1"
scalaVersion  := "3.1.1"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.12-RC2"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.12-RC2" % Test
libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.4"
libraryDependencies += ("org.scala-graph" %% "graph-core" % "1.13.4").cross(CrossVersion.for3Use2_13)
libraryDependencies += "org.apache.commons" % "commons-io" % "1.3.2"
libraryDependencies += "org.scalafx" %% "scalafx" % "17.0.1-R26"
libraryDependencies ++= javaFXModules
libraryDependencies += "com.google.inject" % "guice" % "5.1.0"
libraryDependencies += ("net.codingwell" %% "scala-guice" % "5.0.1").cross(CrossVersion.for3Use2_13)
libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.0.1"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.10.0-RC6"
scalacOptions += "-deprecation"
scalacOptions += "-feature"
scalacOptions +=  "-language:reflectiveCalls"
//coverageExcludedPackages := "de.htwg.se.ScotlandYard.aview.*;" +
//  "de.htwg.se.ScotlandYard.model.fileIOComponent.*;"

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