package org.multibot

object Multibottest {
  val GitterPassEnvName = "multibot.gitter.pass"
  val cache = InterpretersCache(List("#scala", "#scalaz", "#dev-ua/scala"))
  val PRODUCTION = Option(System getenv "multibot.production") exists (_.toBoolean)
  val gitterPass = Option(System getenv GitterPassEnvName).getOrElse(
    "709182327498f5ee393dbb0bc6e440975fa316e5")

  val NUMLINES = 5

  def gitterOutputSanitizer(message: String): Array[String] =
    message
      .replace("\r", "")
      .replace("`", "\'")
      .split("\n")
      .filter(_.nonEmpty)
      .take(NUMLINES)
      .map(m => s"```$m```")

  def ircOutputSanitizer(message: String): Array[String] =
    message
        .replace("\r", "")
        .split("\n")
        .filter(_.nonEmpty)
        .take(NUMLINES)

  val ircMultibot = Multibot(
    identity
    ircOutputSanitizer,
    cache,
    if (PRODUCTION) "multibot_" else "multibot__",
    if (PRODUCTION)
      List("#clojure.pl", "#scala.pl", "#scala", "#scalaz", "#scala-fr", "#lift", "#playframework",
        "#bostonpython", "#fp-in-scala", "#CourseraProgfun", "#shapeless", "#akka", "#sbt", "#scala-monocle")
    else
      List("#multibottest", "#multibottest2")
  )

  val gitterMultibot = Multibot(
    GitterInputSanitizer.sanitize
    outputSanitizer = gitterOutputSanitizer,
    cache = cache,
    botname = if (PRODUCTION) "multibot1" else "multibot2",
    channels = if (PRODUCTION) List("#scala/scala", "#sbt/sbt") else List("#OlegYch/multibot"),
    settings = _.setServerHostname("irc.gitter.im").setServerPassword(gitterPass).
        setSocketFactory(javax.net.ssl.SSLSocketFactory.getDefault)
  )

  def main(args: Array[String]): Unit = {
    ircMultibot.start()
    gitterMultibot.start()
    while (scala.io.StdIn.readLine() != "exit") Thread.sleep(1000)
    sys.exit()
  }
}
