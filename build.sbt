import dependencies._
import sbt.Keys.libraryDependencies

/** Model Module */
lazy val model = (project in file("Model"))
  .settings(
    name := "ScotlandYard-Model",
    version := "0.1.0-SNAPSHOT",
    commonSettings,
    libraryDependencies ++= commonDependencies,
  )

/** Persistence Module */
lazy val persistence = (project in file("Persistence"))
  .dependsOn(model)
  .settings(
    name := "ScotlandYard-Persistence",
    version := "0.1.0-SNAPSHOT",
    commonSettings,
    libraryDependencies ++= commonDependencies,
    resolvers += "jitpack" at "https://jitpack.io"
  )

/** Root Module */
lazy val root = (project in file("."))
  .dependsOn(model, persistence)
  .aggregate(model, persistence)
  .settings(
    name := "ScotlandYard",
    version := "0.1.0-SNAPSHOT",
    commonSettings,
    libraryDependencies ++= commonDependencies,
    scalacOptions ++= commonScalacOptions
  )
  .enablePlugins(JacocoCoverallsPlugin)

/** Common Settings */
lazy val commonSettings = Seq(
  scalaVersion := "3.1.1",
  organization := "de.htwg.se",

  jacocoCoverallsServiceName := "github-actions",
  jacocoCoverallsBranch := sys.env.get("CI_BRANCH"),
  jacocoCoverallsPullRequest := sys.env.get("GITHUB_EVENT_NAME"),
  jacocoCoverallsRepoToken := sys.env.get("COVERALLS_REPO_TOKEN"),
  Test / jacocoExcludes := Seq(
    "de.htwg.se.ScotlandYard.aview.*",
    "de.htwg.se.ScotlandYard.model.fileIOComponent.*"
  ),

  libraryDependencies ++= {
    // Determine OS version of JavaFX binaries
    lazy val osName = System.getProperty("os.name") match {
      case n if n.startsWith("Linux") => "linux"
      case n if n.startsWith("Mac") => "mac"
      case n if n.startsWith("Windows") => "win"
      case _ => throw new Exception("Unknown platform!")
    }

    Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
      .map(m => "org.openjfx" % s"javafx-$m" % "17.0.1" classifier osName)
  }
)

resolvers += "jitpack" at "https://jitpack.io"