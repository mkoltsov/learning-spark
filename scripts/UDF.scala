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

hiveCtx.udf.register("strLenScala", (_:String).length)

val tweetLength = hiveCtx.sql("SELECT strLenScala('tweet') FROM tweets LIMIT 10")
val showCase = hiveCtx.sql("SELECT SUM(user.favouritesCount), SUM(retweetCount), user.id FROM tweets GROUP BY user.id")

//enabling HIVE UDF
hiveCtx.sql("CREATE TEMPORARY FUNCTION name AS class.function").