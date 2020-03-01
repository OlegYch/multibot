package org.multibot

import org.pircbotx.cap.SASLCapHandler

object Multibottest {
  val cache = InterpretersCache(List("#scala", "#scalaz", "#scala/scala"))
  val PRODUCTION = Option(System getenv "multibot.production") exists (_.toBoolean)
  //should be def so that security manager is enabled
  private def gitterPass = Option(System getenv "multibot.gitter.pass").getOrElse(
    "709182327498f5ee393dbb0bc6e440975fa316e5")
  private def freenodePass = Option(System getenv "multibot.freenode.pass")

  val NUMLINES = 5

  def gitterOutputSanitizer(message: String): Array[String] =
    message
      .replace("\r", "")
      .replace("`", "\'")
      .split("\n")
      .filter(_.nonEmpty)
      .take(NUMLINES)
      .map(m => s"`$m`")

  def ircOutputSanitizer(message: String): Array[String] =
    message
      .replace("\r", "")
      .split("\n")
      .filter(_.nonEmpty)
      .take(NUMLINES)

  val ircMultibot = Multibot(
    inputSanitizer = identity,
    outputSanitizer = ircOutputSanitizer,
    cache = cache,
    botname = if (PRODUCTION) "multibot" else "multibot_test",
    channels = if (PRODUCTION)
      List("#scala", "#scalaz", "#playframework", "#fp-in-scala", "#CourseraProgfun", "#sbt", "#scala.pl")
    else
      List("#multibottest", "#multibottest2"),
    settings = _.addServer("irc.freenode.net")
      .setCapEnabled(freenodePass.nonEmpty)
      .addCapHandler(new SASLCapHandler("multibot", freenodePass.getOrElse("")))
  )

  val gitterMultibot = Multibot(
    inputSanitizer = GitterInputSanitizer.sanitize,
    outputSanitizer = gitterOutputSanitizer,
    cache = cache,
    botname = if (PRODUCTION) "multibot1" else "multibot2",
    channels = if (PRODUCTION) List("#scala/scala", "#scalaz/scalaz", "#OlegYch/multibot") else List("#OlegYch/multibot"),
    settings = _.addServer("irc.gitter.im").setServerPassword(gitterPass).
      setSocketFactory(javax.net.ssl.SSLSocketFactory.getDefault)
  )

  def main(args: Array[String]): Unit = {
    ircMultibot.start()
    gitterMultibot.start()
    while (scala.io.StdIn.readLine() != "exit") Thread.sleep(1000)
    sys.exit()
  }
}
