package com.example.hivetest

import cats.effect._
import cats.implicits._
import fs2.Stream
import org.http4s._
import org.http4s.dsl._
import org.http4s.headers.`Content-Type`

object Routes {
  def bestRatedRoutes[F[_]: Async](B: BestRated[F], filePath: String): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F]{}
    import dsl._
    HttpRoutes.of[F] {

      //successfully return the body
      case req @ POST -> Root / "test" => Ok(req.body)

      //successfully get a best rated result in JSON (JSON decoders working :))
      case POST -> Root / "test2" =>
        val eff: Stream[F, Result] = Stream.eval(Sync[F].delay(Result("test", 2.0)))
        Ok(eff)

      // the getBestRated function is actually working, because it prints to STDOUT
      // and the println in the function works too
      // it must be parsing the request into the Search type that isn't working
      case POST -> Root / "test4" => Ok(B.getBestRated(Search("start", "end", 1, 1), ""))

      // trying to understand what the search encoder would look like?
      //{"start":"s","end":"e","limit":1,"minNumberReviews":1}
      case POST -> Root / "test5" => Ok(Search("s", "e", 1, 1))

      // This uses the request body in the response
      // this is very similar to test1
      case req @ POST -> Root / "test6" =>
        Ok(req.body).map(_.putHeaders(`Content-Type`(MediaType.text.plain)))

      // TODO keep getting error that request body was invalid
      // need to learn how to turn the request body into my case class
      // and ideally return in response so I can see it's working before I can get the route below to work
      case req @ POST -> Root / "test7" => Ok(req.body.as[Search](Search("", "", 1, 1)))

      //TODO this is the route I need to create - still need to get this to work
      case req @ POST -> Root / "amazon" / "best-rated" =>
        for {
          search <- req.as[Search]
          response <- Ok(B.getBestRated(search, filePath))
        } yield response
    }
  }
}