package com.example.hivetest

import cats.effect.{ExitCode, IO, IOApp}
import pureconfig._
import pureconfig.generic.auto._

object Main extends IOApp {
  def run(args: List[String]): IO[ExitCode] = {
    ConfigSource.default.load[Config].map(_.filePath) match {
      case Left(_) => IO(ExitCode.Error)
      case Right(file) => Server.stream[IO](file).compile.drain.as(ExitCode.Success)
    }
  }
}
