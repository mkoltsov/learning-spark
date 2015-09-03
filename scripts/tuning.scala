val conf = new SparkConf()

conf.set("spark.app.name", "My Spark App")
conf.set("spark.app.master", "local[4]")
conf.set("spark.ui.port", "36000")

val sc = new SparkContext(conf)

// achieving the same results through the spark-submit parameters
$ bin/spark-submit \
  --class com.example.MyApp \
  --master local[4] \
  --name "My Spark App" \
  --conf spark.ui.port=36000 \
  myApp.jar

 // Setting configuration values at runtime using a defaults file
$ bin/spark-submit \
  --class com.example.MyApp \
  --properties-file my-config.conf \
  myApp.jar

## Contents of my-config.conf ##
spark.master    local[4]
spark.app.name  "My Spark App"
spark.ui.port   36000