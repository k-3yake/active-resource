name := """active-resource"""

version := "1.0-SNAPSHOT"

scalacOptions += "-Xprint:typer"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws
)

libraryDependencies += "com.h2database" % "h2" % "1.4.179"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.1.0" % "test"
