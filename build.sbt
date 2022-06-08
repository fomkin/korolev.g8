organization := "com.example"

name := "$name$"

version := "$version$"

scalaVersion := "$scala_version$"

val korolevVersion = "$korolev_version$"

libraryDependencies ++= Seq(
  "org.fomkin" %% "korolev-standalone" % korolevVersion
)
