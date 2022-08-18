package com.example.hivetest

import cats.effect.{Async, Resource}
import cats.syntax.all._
import com.comcast.ip4s._
import fs2.Stream
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.implicits._
import org.http4s.server.middleware.Logger

object Server {
  def stream[F[_]: Async](filePath: String): Stream[F, Nothing] = {
    for {
      exitCode <- Stream.resource(
        EmberServerBuilder.default[F]
          .withHost(ipv4"0.0.0.0")
          .withPort(port"8080")
          .withHttpApp(Logger.httpApp(true, true)(Routes.bestRatedRoutes[F](BestRated.impl[F], filePath).orNotFound))
          .build >>
        Resource.eval(Async[F].never)
      )
    } yield exitCode
  }.drain
}
