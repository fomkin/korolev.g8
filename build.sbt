organization := "com.example"

name := "$name$"

version := "$version$"

scalaVersion := "$scala_version$"

val korolevVersion = "$korolev_version$"

libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-simple" % "1.7.+",
  "com.github.fomkin" %% "korolev-akka" % korolevVersion
)
