package com.example.hivetest

import cats.effect.IO
import org.http4s._
import org.http4s.implicits._
import munit.CatsEffectSuite

class BestRatedSpec extends CatsEffectSuite {

  test("BestRatedTest returns status code 200") {
    assertIO(BestRatedTest.map(_.status) ,Status.Ok)
  }

  test("BestRatedTest returns expected output") {
    assertIO(BestRatedTest.flatMap(_.as[String]), "{\"asin\":\"test\",\"averageRating\":2.0}")
  }

  private[this] val BestRatedTest: IO[Response[IO]] = {
    val postBR = Request[IO](Method.POST, uri"/test2")
    val bestRated = BestRated.impl[IO]
    Routes.bestRatedRoutes(bestRated, "file").orNotFound(postBR)
  }
}