lazy val impl = (project in file("impl"))
  .settings(
    name := "minute-adder",
    version := "0.1",
    scalaVersion := "2.12.17",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.1.1" % "test"
    ),
  )