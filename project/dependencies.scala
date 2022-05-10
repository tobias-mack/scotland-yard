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
	val akkaHttp = ("com.typesafe.akka" %% "akka-http" % versionNumber.akkaHttpVersion).cross(CrossVersion.for3Use2_13)
	val akkaHttpSpray = ("com.typesafe.akka" %% "akka-http-spray-json" % versionNumber.akkaHttpVersion).cross(CrossVersion.for3Use2_13)
	val akkaHttpCore = ("com.typesafe.akka" %% "akka-http-core" % versionNumber.akkaHttpVersion).cross(CrossVersion.for3Use2_13)
	val akkaActorTyped = ("com.typesafe.akka" %% "akka-actor-typed" % versionNumber.akka).cross(CrossVersion.for3Use2_13)
	val akkaStream = ("com.typesafe.akka" %% "akka-stream" % versionNumber.akka).cross(CrossVersion.for3Use2_13)
	val akkaActor = ("com.typesafe.akka" %% "akka-actor" % versionNumber.akka).cross(CrossVersion.for3Use2_13)
	val slf4jNop = "org.slf4j" % "slf4j-nop" % versionNumber.slf4jNop
	val slick = ("com.typesafe.slick" %% "slick" % versionNumber.slick).cross(CrossVersion.for3Use2_13)
	val logback = ("ch.qos.logback" % "logback-classic" % versionNumber.logback).cross(CrossVersion.for3Use2_13)
	val hikarislick = ("com.typesafe.slick" %% "slick-hikaricp" % versionNumber.hikarislick).cross(CrossVersion.for3Use2_13)
	val githubslick =("com.github.slick.slick" % "slick_3" % versionNumber.githubslick)
	val postgresql = "org.postgresql" % "postgresql" % versionNumber.postgresql

	object versionNumber {
		val akka = "2.6.19"
		val akkaHttpVersion = "10.2.9"
		val slf4jNop = "2.0.0-alpha7"
		val slick = "3.3.3"
		val logback = "1.1.12"
		val hikarislick = "3.3.3"
		val githubslick = "nafg~dottyquery-SNAPSHOT"
		val postgresql = "42.3.4"
	}
	//scalacOptions
	val scalacDeprecation = "-deprecation"
	val scalacFeature = "-feature"
	val scalacreflectiveCalls =  "-language:reflectiveCalls"

	// Projects
	val commonDependencies =
		Seq(scalactic, scalatest, apacheCommons, apacheCommonsIO, scalaGraph,	scalaFX,
			googleGuice,	scalaGuice,	scalaXml, playJson, akkaHttp, akkaHttpSpray, akkaHttpCore,
			akkaActorTyped, akkaStream, akkaActor, slf4jNop)

	val commonScalacOptions = Seq(scalacDeprecation, scalacFeature, scalacreflectiveCalls)
}