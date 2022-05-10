import sbt._

object dependencies {
  val scalactic = "org.scalactic" %% "scalactic" % versionNumber.scalactic
  val scalatest = "org.scalatest" %% "scalatest" % versionNumber.scalatest % "test"

  val scalafx = "org.scalafx" %% "scalafx" % versionNumber.scalafx

  val codingwell = ("net.codingwell" %% "scala-guice" % versionNumber.codingwell).cross(CrossVersion.for3Use2_13)

  val googleinject = "com.google.inject" % "guice"% versionNumber.googleinject

  val scalalangmodules = "org.scala-lang.modules" %% "scala-xml" % versionNumber.scalalangmodules

  val typesafeplay = "com.typesafe.play" %% "play-json" % versionNumber.typesafeplay


  val akkaHttp = ("com.typesafe.akka" %% "akka-http" % versionNumber.akkaHttpVersion).cross(CrossVersion.for3Use2_13)
  val akkaHttpSpray = ("com.typesafe.akka" %% "akka-http-spray-json" % versionNumber.akkaHttpVersion).cross(CrossVersion.for3Use2_13)
  val akkaHttpCore = ("com.typesafe.akka" %% "akka-http-core" % versionNumber.akkaHttpVersion).cross(CrossVersion.for3Use2_13)
  val akkaActorTyped = ("com.typesafe.akka" %% "akka-actor-typed" % versionNumber.akka).cross(CrossVersion.for3Use2_13)
  val akkaStream = ("com.typesafe.akka" %% "akka-stream" % versionNumber.akka).cross(CrossVersion.for3Use2_13)
  val akkaActor = ("com.typesafe.akka" %% "akka-actor" % versionNumber.akka).cross(CrossVersion.for3Use2_13)
  val slf4jNop = "org.slf4j" % "slf4j-nop" % versionNumber.slf4jNop
}

object versionNumber {
  val akka = "2.6.19"
  val akkaHttpVersion = "10.2.9"
  val scalactic = "3.2.11"
  val scalatest = "3.2.11"
  val scalafx = "17.0.1-R26"
  val googleinject = "5.1.0"
  val codingwell = "5.0.1"
  val scalalangmodules = "2.0.1"
  val typesafeplay = "2.10.0-RC6"
  val slf4jNop = "2.0.0-alpha7"
}