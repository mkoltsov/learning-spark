val conf = new SparkConf()

conf.set("spark.app.name", "My Spark App")
conf.set("spark.app.master", "local[4]")
conf.set("spark.ui.port", "36000")

val sc = new SparkContext(conf)