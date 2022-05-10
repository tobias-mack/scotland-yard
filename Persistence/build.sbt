name := "scotland-yard-persistence"
organization  := "de.htwg.se.scotland-yard"
version       := "0.0.1-SNAPSHOT"
scalaVersion := "3.1.1"

lazy val commonDependencies = Seq(
  dependencies.scalactic,
  dependencies.scalatest,
  dependencies.scalafx,
  dependencies.codingwell,
  dependencies.akkaHttp,
  dependencies.akkaHttpSpray,
  dependencies.akkaHttpCore,
  dependencies.slf4jNop,
  dependencies.googleinject,
  dependencies.scalalangmodules,
  dependencies.typesafeplay,
  dependencies.akkaActor,
  dependencies.akkaStream,
  dependencies.akkaActorTyped
)

libraryDependencies ++= commonDependencies

