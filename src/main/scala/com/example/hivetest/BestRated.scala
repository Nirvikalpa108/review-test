package com.example.hivetest

import cats.effect.Sync
import com.example.hivetest.Utils.{aggregateReviews, getReviewAverage, isWithinTimePeriod}
import fs2.Stream
import fs2.io.file.{Files, Path}
import io.circe.fs2.{decoder, stringStreamParser}


trait BestRated[F[_]]{
  def getBestRated(searchQuery: Search, filePath: String): Stream[F, Result]
}

object BestRated {
  def impl[F[_]: Sync : Files]: BestRated[F] = new BestRated[F] {
    def getBestRated(search: Search, filePath: String): Stream[F, Result] = {
//      Files[F].readUtf8Lines(Path(filePath))
//        .through(stringStreamParser)
//        .through(decoder[F, Review])
//        .filter(review => isWithinTimePeriod(search.start, search.end, review.unixReviewTime))
//        .foldMap(aggregateReviews)
//        .filter(_.reviews._2.size < search.minNumberReviews)
//        .map(getReviewAverage)
//        .take(search.limit.toLong)
//      //TODO consider how to sort stream by highest average rating before calling take

      Files[F].readUtf8Lines(Path(filePath))
        .through(stringStreamParser)
        .through(decoder[F, Review])
        .map(review => Result(review.asin, review.overall))
    }
  }
}
