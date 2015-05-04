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

scalaVersion := "2.11.6"

resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"

libraryDependencies ++= {
  val scalazVersion = "7.1.1"
  val scalazStreamVersion = "0.6a"
  val shapelessVersion = "2.0.0" //todo update to 2.1 once monocle is updated
  val monocleVersion = "1.0.1"
  val spireVersion = "0.9.0"
  Seq(
    "org.scalaz" %% "scalaz-iteratee" % scalazVersion,
    "org.scalaz" %% "scalaz-effect" % scalazVersion,
    "org.scalaz" %% "scalaz-typelevel" % scalazVersion,
    "org.scalaz" %% "scalaz-scalacheck-binding" % scalazVersion,
    "org.scalaz" %% "scalaz-concurrent" % scalazVersion,
    "com.chuusai" %% "shapeless" % shapelessVersion,
    "com.github.julien-truffaut" %% "monocle-generic" % monocleVersion,
    "com.github.julien-truffaut" %% "monocle-law" % monocleVersion,
    "com.github.julien-truffaut" %% "monocle-macro" % monocleVersion,
    "org.spire-math" %% "spire" % spireVersion,
    "org.scalaz.stream" %% "scalaz-stream" % scalazStreamVersion
  )
}

libraryDependencies ++= Seq(
  "org.pircbotx" % "pircbotx" % "2.0.1",
  "org.slf4j" % "slf4j-simple" % "1.7.10",
  "org.scala-lang" % "scala-compiler" % scalaVersion.value,
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "net.databinder" %% "dispatch-http" % "0.8.10",
  "org.json4s" %% "json4s-native" % "3.2.10",
  "com.google.guava" % "guava" % "18.0"
)

autoCompilerPlugins := true

scalacOptions ++= Seq("-feature:false", "-language:_", "-deprecation", "-Xexperimental", "-YclasspathImpl:flat")

resolvers += "Sonatype Nexus Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"

resolvers += "Linter Repository" at "https://hairyfotr.github.io/linteRepo/releases"

addCompilerPlugin("com.foursquare.lint" %% "linter" % "0.1.8")

libraryDependencies += "com.foursquare.lint" %% "linter" % "0.1.8"

//scalacOptions += "-P:linter:disable:OLOLOUseHypot+CloseSourceFile+OptionOfOption"

addCompilerPlugin("org.brianmckenna" %% "wartremover" % "0.11")

//libraryDependencies += "org.brianmckenna" %% "wartremover" % "0.11"
//
//scalacOptions += "-P:wartremover:only-warn-traverser:org.brianmckenna.wartremover.warts.Unsafe"

buildInfoSettings

sourceGenerators in Compile <+= buildInfo

buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion, scalacOptions in(Compile, compile), libraryDependencies in(Compile, compile))

buildInfoPackage := "org.multibot"

resolvers += "bintray/non" at "http://dl.bintray.com/non/maven"

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.5.2")

libraryDependencies += "org.spire-math" %% "kind-projector" % "0.5.2"

