package com.example.hivetest

import cats.Monoid
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}
import org.http4s.{EntityDecoder, EntityEncoder}
import org.http4s.circe.{jsonEncoderOf, jsonOf}
import cats.effect.Concurrent


case class Search(start: String, end: String, limit: Int, minNumberReviews: Int)
object Search {
  implicit val searchEncoder: Encoder[Search] = deriveEncoder[Search]
  implicit val searchDecoder: Decoder[Search] = deriveDecoder[Search]

  implicit def searchEntityEncoder[F[_]]: EntityEncoder[F, Search] = jsonEncoderOf[F, Search]

  implicit def searchEntityDecoder[F[_] : Concurrent]: EntityDecoder[F, Search] = jsonOf[F, Search]
}

case class Review(asin: String, helpful: List[Int], overall: Double, reviewText: String, reviewerId: String, reviewerName: String, summary: String, unixReviewTime: Long)
object Review {
  implicit val reviewDecoder: Decoder[Review] = deriveDecoder[Review]
}

case class ProductReview(reviews: (String, List[Review]))
  object ProductReview {
    implicit val monoidProductReview: Monoid[ProductReview] = new Monoid[ProductReview] {
      override def empty: ProductReview = ???
      override def combine(x: ProductReview, y: ProductReview): ProductReview = ???
    }
  }

case class Result(asin: String, averageRating: Double)
object Result {
  implicit val resultEncoder: Encoder[Result] = deriveEncoder[Result]

  implicit def resultEntityEncoder[F[_]]: EntityEncoder[F, Result] = jsonEncoderOf[F, Result]
}

case class Config(filePath: String)