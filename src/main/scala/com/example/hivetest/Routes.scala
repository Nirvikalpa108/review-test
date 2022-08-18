package com.example.hivetest

import cats.effect._
import cats.implicits._
import org.http4s._
import org.http4s.dsl._

object Routes {
  def bestRatedRoutes[F[_]: Async](B: BestRated[F], filePath: String): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F]{}
    import dsl._
    HttpRoutes.of[F] {
      case req @ POST -> Root / "amazon" / "best-rated" =>
        for {
          search <- req.as[Search]
          response <- Ok(B.getBestRated(search, filePath))
        } yield response
    }
  }
}