## What is this?
This is a largely unfinished programme, which gives a broad indication of how I would write a programme to process a file
of JSON objects separated by new lines, given terms from an API request body and serving the result in an API response. 

Unfortunately I've run out of personal time to complete it, so I'm showing what I have so far!

### How can I run this programme? 
You can clone the repo, navigate to the root directory and run `sbt run` on the command line to start the server locally. 
In another terminal window you can run the following to make an API request:
```
curl -X POST http://localhost:8080/amazon/best-rated -H "Content-Type: application/json" -d '{"start": "01.01.2010","end": "31.12.2020","limit": 2,"minNumberReviews": 2}'
```

### What should I be looking at to get a sense of how you approached this problem?
I've made a short video to walk you through the code: https://www.loom.com/share/4eb6bc08c025431bb705ee82a7e95725.

The main point is that the commented out code in `BestRated.scala` is the main thing I wanted to complete and the function 
signatures for this are located in `Utils`. If I had more time, I would write unit tests for these functions and finish 
implementing them. The main thing I am unsure about is how to implement some kind of sorting so that I can provide the 
highest rated products.

### What are some of the assumptions you made?
1. the POST request body follows JSON protocols so that it works with the http4s circe decoders
1. assumed the file is not zipped
1. assumed average rating meant the mean average. I also wondered how many decimal points to return.

### What were some of the questions you had when approaching this?
1. how can I think creatively around sorting a stream of products with the highest average rating? 
1. will there be any constraints on the limit value? What if the limit is very high? How will the programme manage this?
1. what are the constraints on the dates given in the request? It would be good to write unit tests for all the possible date scenarios. For example, what if the end date is in the future? How will the programme respond?
1. caching - what if the same request is repeated many times by the user? It's hard to know without understanding the use case for this API. Would caching by useful?
