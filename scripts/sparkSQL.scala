// Import Spark SQL
import org.apache.spark.sql.hive.HiveContext
// Or if you can't have the hive dependencies
//import org.apache.spark.sql.SQLContext
//val sc = new SparkContext(...)

// Create a Spark SQL HiveContext
val hiveCtx = new HiveContext(sc)
// Import the implicit conversions
import hiveCtx.implicits._

val input = hiveCtx.jsonFile("testweet.json")

input.registerTempTable("tweets")

val topTweets = hiveCtx.sql("SELECT text, retweetCount FROM tweets ORDER BY retweetCount LIMIT 10")

println(topTweets.rdd.collect())
println("SHOW")
println(topTweets.show())

println(topTweets.select("text").show)
println("FILTER")
topTweets.filter("retweetCount>1").show
println("GROUP BY + MIN")
//also min(), max(), mean() or agg() work
println(topTweets.groupBy(topTweets("text")).min().show())