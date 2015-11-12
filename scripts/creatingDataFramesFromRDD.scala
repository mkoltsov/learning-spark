case class HappyPerson(handle:String, favoriteBeverage:String)

val happyRDD = sc.parallelize(List(HappyPerson("holden", "coffee")))

// that is equivalent to sqlCtx.createDataFrame(happyPeopleRDD)	

// happyRDD.registerTempTable("happy_people")

import org.apache.spark.sql.SQLContext
val sqlContext = new SQLContext(sc)
import sqlContext.implicits._
println(happyRDD.toDF().show())