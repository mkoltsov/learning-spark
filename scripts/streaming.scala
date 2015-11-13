// groupId = org.apache.spark
// artifactId = spark-streaming_2.10
// version = 1.3.0
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.Duration
import org.apache.spark.streaming.Seconds

val ssc = new StreamingContext(conf, Seconds(1))
val lines = ssc.socketTextStream("localhost", 7777)
val errorLines = lines.filter(_.contains("error"))

val accessLogsWindow = accessLogsDStream.window(Seconds(30), Seconds(10))
val windowCounts = accessLogsWindow.count()

val ipDStream = accessLogsDStream.map(logEntry => (logEntry.getIpAddress(), 1))

val ipCountDStream = ipDStream.reduceByKeyAndWindow(
  {(x, y) => x + y}, // Adding elements in the new batches entering the window
  {(x, y) => x - y}, // Removing elements from the oldest batches exiting the window
  Seconds(30),       // Window duration
  Seconds(10))       // Slide duration)

val ipDStream = accessLogsDStream.map{entry => entry.getIpAddress()}
val ipAddressRequectCount = ipDStream.countByValueAndWindow(Seconds(30), Seconds(10))
val requestCount = accessLogsDStream.countByWindow(Seconds(30), Seconds(10))

def updateRunningSum(values: Seq[Long], state:Option[Long]) = {Some(state.getOrElse(0L) + values.size)}

val responseCodeDStream = accessLogsDStream.map(log => (log.getResponseCode(), 1L))
val responseCodeCountDStream = responseCodeDStream.updateStateByKey(updateRunningSum _)

errorLines.print()

errorLines.saveAsTextFiles("outputDir", "txt")

val writableIpAddressRequestCount = ipAddressRequestCount.map {
  (ip, count) => (new Text(ip), new LongWritable(count)) }
writableIpAddressRequestCount.saveAsHadoopFiles[
  SequenceFileOutputFormat[Text, LongWritable]]("outputDir", "txt")

ipAddressRequestCount.foreachRDD { rdd =>
  rdd.foreachPartition { partition =>
    // Open connection to storage system (e.g. a database connection)
    partition.foreach { item =>
      // Use connection to push item to system
    }
    // Close connection
  }
}

ssc.start()

ssc.awaitTermination()