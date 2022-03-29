resolvers += Classpaths.sbtPluginReleases

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "5.2.3")

addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "1.0.0")

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.6.1")
//scoverage plugin is not yet available for scala 3
//track the progress here https://github.com/scoverage/scalac-scoverage-plugin/issues/299
addSbtPlugin("org.scoverage" % "sbt-coveralls" % "1.2.7")



