import sbt._

object dependencies {
	// Versions
	lazy val scalacticVersion = "3.2.12-RC2"

	// Libraries
	val scalactic = "org.scalactic" %% "scalactic" % scalacticVersion
	val scalatest = "org.scalatest" %% "scalatest" % scalacticVersion % Test
	val apacheCommons = "org.apache.commons" % "commons-lang3" % "3.4"
	val apacheCommonsIO= "org.apache.commons" % "commons-io" % "1.3.2"
	val scalaGraph = ("org.scala-graph" %% "graph-core" % "1.13.4").cross(CrossVersion.for3Use2_13)
	val scalaFX = "org.scalafx" %% "scalafx" % "17.0.1-R26"
	val googleGuice = "com.google.inject" % "guice" % "5.1.0"
	val scalaGuice = ("net.codingwell" %% "scala-guice" % "5.0.1").cross(CrossVersion.for3Use2_13)
	val scalaXml = "org.scala-lang.modules" %% "scala-xml" % "2.0.1"
	val playJson = "com.typesafe.play" %% "play-json" % "2.10.0-RC6"

	//scalacOptions
	val scalacDeprecation = "-deprecation"
	val scalacFeature = "-feature"
	val scalacreflectiveCalls =  "-language:reflectiveCalls"

	// Projects
	val commonDependencies =
		Seq(scalactic, scalatest, apacheCommons, apacheCommonsIO, scalaGraph,	scalaFX,
			googleGuice,	scalaGuice,	scalaXml, playJson)

	val commonScalacOptions = Seq(scalacDeprecation, scalacFeature, scalacreflectiveCalls)
}