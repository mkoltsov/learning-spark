// Import Spark SQL
import org.apache.spark.sql.hive.HiveContext
// Or if you can't have the hive dependencies
//import org.apache.spark.sql.SQLContext
//val sc = new SparkContext(...)

// Create a Spark SQL HiveContext
val hiveCtx = new HiveContext(sc)
// Import the implicit conversions
import hiveCtx.implicits._

