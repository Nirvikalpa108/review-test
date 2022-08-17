package com.example.hivetest

object Utils {
  // determine whether the unix time is within the two string date times
  def isWithinTimePeriod(startDate: String, endDate: String, reviewDate: Long): Boolean = ???

  // put the review into the product review list
  def aggregateReviews(review: Review): ProductReview = ???

  def getReviewAverage(prodReview: ProductReview): Result = ???

  // TODO do I need to write my own sorting function for the stream?
  def sortByRating(result: Result): Result = ???
}
