resolvers += Classpaths.sbtPluginReleases

addSbtPlugin("com.github.sbt" % "sbt-jacoco" % "3.4.0")
//addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.6.1")
//scoverage plugin is not yet available for scala 3
//track the progress here https://github.com/scoverage/scalac-scoverage-plugin/issues/299
addSbtPlugin("org.scoverage" % "sbt-coveralls" % "1.2.7")



