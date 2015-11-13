def createStreamingContext() = {
  ...
  val sc = new SparkContext(conf)
  // Create a StreamingContext with a 1 second batch size
  val ssc = new StreamingContext(sc, Seconds(1))
  ssc.checkpoint(checkpointDir)
}
...
val ssc = StreamingContext.getOrCreate(checkpointDir, createStreamingContext _)

// Launching a driver in supervise mode
./bin/spark-submit --deploy-mode cluster --supervise --master spark://... App.jar

// Enable the Concurrent Mark-Sweep GC
spark-submit --conf spark.executor.extraJavaOptions=-XX:+UseConcMarkSweepGC App.jar