name          := "ScotlandYard"
organization  := "de.htwg.se"
version       := "0.0.1"
scalaVersion  := "2.13.5"

//libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.5.1")
addSbtPlugin("org.scoverage" % "sbt-coveralls" % "1.2.7")

//libraryDependencies += "junit" % "junit" % "4.8" % "test"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.7"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.7" % "test"
libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.4"
libraryDependencies += "org.apache.commons" % "commons-io" % "1.3.2"



//*******************************************************************************//
//Libraries that we will use in later lectures compatible with this scala version
// uncomment to use!!

//libraryDependencies += "org.scala-lang.modules" % "scala-swing_2.12" % "2.0.1"

//libraryDependencies += "com.google.inject" % "guice" % "4.1.0"

//libraryDependencies += "net.codingwell" %% "scala-guice" % "4.1.0"

//libraryDependencies += "org.scala-lang.modules" % "scala-xml_2.12" % "1.0.6"

//libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.6"
