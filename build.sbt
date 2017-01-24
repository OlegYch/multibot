import sbt.Keys._
import sbt._
import sbtbuildinfo.Plugin._

name := "multibot"

version := "1.0"

fork in run := true

connectInput in run := true

mainClass in Compile := Some("org.multibot.Multibottest")

updateOptions := updateOptions.value.withCachedResolution(true).withLatestSnapshots(false)

publishArtifact in(Compile, packageDoc) := false

enablePlugins(JavaAppPackaging)

scalaVersion := "2.12.1"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= {
  val scalazVersion = "7.2.8"
  val scalazStreamVersion = "0.8.6a"
  val shapelessVersion = "2.3.2"
  val monocleVersion = "1.4.0-M1"
  val spireVersion = "0.13.0"
  Seq(
    "org.scalaz" %% "scalaz-iteratee" % scalazVersion,
    "org.scalaz" %% "scalaz-effect" % scalazVersion,
    "org.scalaz" %% "scalaz-scalacheck-binding" % scalazVersion,
    "org.scalaz" %% "scalaz-concurrent" % scalazVersion,
    "com.chuusai" %% "shapeless" % shapelessVersion,
    "com.github.julien-truffaut" %% "monocle-generic" % monocleVersion,
    "com.github.julien-truffaut" %% "monocle-law" % monocleVersion,
    "com.github.julien-truffaut" %% "monocle-macro" % monocleVersion,
    "org.spire-math" %% "spire" % spireVersion,
    "org.scalaz.stream" %% "scalaz-stream" % scalazStreamVersion
//    "org.pelotom" %% "effectful" % "1.1-SNAPSHOT"
  )
}

libraryDependencies ++= Seq(
  "org.pircbotx" % "pircbotx" % "2.0.1",
  "org.slf4j" % "slf4j-simple" % "1.7.10",
  "org.scala-lang" % "scala-compiler" % scalaVersion.value,
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "org.http4s" %% "http4s-client" % "0.15.0a",
  "org.json4s" %% "json4s-native" % "3.5.0",
  "com.google.guava" % "guava" % "18.0",
  "org.scalactic" %% "scalactic" % "3.0.1",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)

autoCompilerPlugins := true

scalacOptions ++= Seq("-feature:false", "-language:_", "-deprecation", "-Xexperimental")

addCompilerPlugin("org.psywerx.hairyfotr" %% "linter" % "0.1.17")

libraryDependencies += "org.psywerx.hairyfotr" %% "linter" % "0.1.17"

//scalacOptions += "-P:linter:disable:OLOLOUseHypot+CloseSourceFile+OptionOfOption"

//addCompilerPlugin("org.brianmckenna" %% "wartremover" % "0.14")

//libraryDependencies += "org.brianmckenna" %% "wartremover" % "0.14"
//
//scalacOptions += "-P:wartremover:only-warn-traverser:org.brianmckenna.wartremover.warts.Unsafe"

buildInfoSettings

sourceGenerators in Compile <+= buildInfo

buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion, scalacOptions in(Compile, compile), libraryDependencies in(Compile, compile))

buildInfoPackage := "org.multibot"

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.3")

libraryDependencies += "org.spire-math" %% "kind-projector" % "0.9.3"

