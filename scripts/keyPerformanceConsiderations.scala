# Wildcard input that may match thousands of files
>>> input = sc.textFile("s3n://log-files/2014/*.log")
>>> input.getNumPartitions()
35154
# A filter that excludes almost all data
>>> lines = input.filter(lambda line: line.startswith("2014-10-17"))
>>> lines.getNumPartitions()
35154
# We coalesce the lines RDD before caching
>>> lines = lines.coalesce(5).cache()
>>> lines.getNumPartitions()
5
# Subsequent analysis can operate on the coalesced RDD...
>>> lines.count()

// use repartition() in case you need to reshuffle & redistribute the data

// Using the Kryo serializer and registering classes
val conf = new SparkConf()
conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
// Be strict about class registration
conf.set("spark.kryo.registrationRequired", "true")
conf.registerKryoClasses(Array(classOf[MyClass], classOf[MyOtherClass]))

// enable the serialization debugging
"-Dsun.io.serialization.extended DebugInfo=true"
// available through  --driver-java-options and --executor-java-options flags to spark-submit