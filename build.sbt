import sbt.Keys.libraryDependencies

lazy val root = (project in file("."))
  .settings(
        name := "gatling-openpaas",
        cancelable in Global := true,
        version := "0.1-SNAPSHOT",
        scalaVersion := "2.12.8",

        libraryDependencies += "io.gatling" % "gatling-test-framework" % gatlingVersion,
        libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % gatlingVersion,
        libraryDependencies += "com.github.azakordonets" %% "fabricator" % "2.1.5",
        libraryDependencies += "com.typesafe.play" %% "play-ahc-ws-standalone" % playWsVersion

  )

val playWsVersion = "2.0.1"
val gatlingVersion = "3.0.3"

enablePlugins(GatlingPlugin)
