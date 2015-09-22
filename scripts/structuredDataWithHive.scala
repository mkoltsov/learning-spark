import org.apache.spark.sql.hive.HiveContext

// JSON
val hiveCtx = new HiveContext(sc);
val tweets = hiveCtx.jsonFile("tweets.json")
tweets.registerTempTable("tweets")
val results = hiveCtx.sql("SELECT user.name, text FROM tweets")

//SQL
val hiveCtx = new HiveContext(sc)
val rows = hiveCtx.sql("SELECT name, age FROM users")
val firstRow = rows.first()
println(firstRow.getString(0)) // Field 0 is the name