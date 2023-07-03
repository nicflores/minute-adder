lazy val impl = (project in file("impl"))
  .settings(
    name := "minute-adder",
    version := "0.1",
    scalaVersion := "2.12.17",
    libraryDependencies ++= Seq(
      "org.scala-lang.modules" %% "scala-parser-combinators" % "2.3.0",
      "org.scalatest" %% "scalatest" % "3.2.16" % Test,
      "org.scalacheck" %% "scalacheck" % "1.17.0" % Test
    )
  )
