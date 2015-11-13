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