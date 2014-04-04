import play.Project._

name := "computer-database"

version := "1.0"

libraryDependencies ++= Seq(javaJdbc, javaEbean)

playJavaSettings

libraryDependencies += "postgresql" % "postgresql" % "8.4-702.jdbc4"