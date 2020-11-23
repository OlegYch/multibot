name := "multibot"
version := "1.0"
fork := true
connectInput := true
mainClass in Compile := Some("org.multibot.Multibottest")
updateOptions := updateOptions.value.withCachedResolution(true).withLatestSnapshots(false)
publishArtifact in(Compile, packageDoc) := false
enablePlugins(JavaAppPackaging)
scalaVersion := "2.13.4"

libraryDependencies ++= {
  val scalazVersion = "7.3.2"
  val shapelessVersion = "2.3.3"
  val monocleVersion = "1.6.0-RC1"
  Seq(
    "org.scalaz" %% "scalaz-iteratee" % scalazVersion,
    "org.scalaz" %% "scalaz-effect" % scalazVersion,
    "com.chuusai" %% "shapeless" % shapelessVersion,
    "com.github.julien-truffaut" %% "monocle-generic" % monocleVersion,
    "com.github.julien-truffaut" %% "monocle-law" % monocleVersion,
    "com.github.julien-truffaut" %% "monocle-macro" % monocleVersion,
  )
}

libraryDependencies ++= Seq(
  "org.pircbotx" % "pircbotx" % "2.1",
  "org.slf4j" % "slf4j-simple" % "1.7.10",
  "com.google.guava" % "guava" % "18.0",
  "org.scala-lang" % "scala-compiler" % scalaVersion.value,
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "org.json4s" %% "json4s-native" % "3.6.7",
  "org.scalactic" %% "scalactic" % "3.0.8",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test",
)


scalacOptions ++= Seq("-feature:false", "-language:_", "-deprecation")

autoCompilerPlugins := true

addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.1" cross CrossVersion.full)
libraryDependencies += "org.typelevel" %% "kind-projector" % "0.11.1" cross CrossVersion.full

enablePlugins(BuildInfoPlugin)
buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion, scalacOptions in(Compile, compile), libraryDependencies in(Compile, compile))
buildInfoPackage := "org.multibot"


