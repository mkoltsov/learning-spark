val logData = ssc.textFileStream(logDirectory)

// Streaming SequenceFiles written to a directory in Scala

ssc.fileStream[LongWritable, IntWritable,
   SequenceFileInputFormat[LongWritable, IntWritable]](inputDirectory).map {
   case (x, y) => (x.get(), y.get())
}

//using Kafka

import org.apache.spark.streaming.kafka._

val topics = List(("pandas", 1), ("logs", 1)).toMap
val topicLines = KafkaUtils.createStream(ssc, zkQuorum, group, topics)
topicLines.print()

//using Kafka directly

import org.apache.spark.streaming.kafka._
import kafka.serializer.StringDecoder
...
// Specify the brokers in our kafkaParams
val kafkaParams = Map[String, String]("metadata.broker.list" -> brokers)
// Create a map of topics to number of receiver threads to use
val topicSet = List("pandas", "logs").toSet
val topicLines = KafkaUtils.createDirectStream[String, String,
  StringDecoder, StringDecoder](ssc, kafkaParams, topicSet)
StreamingLogInput.processLines(topicLines.map(_._2))

//push-based receiver from Flume

val events = FlumeUtils.createStream(ssc, receiverHostname, receiverPort)

//pull from Flume sink example 
val events = FlumeUtils.createPollingStream(ssc, receiverHostname, receiverPort)

// SparkFlumeEvent in Scala
// Assuming that our flume events are UTF-8 log lines
 val lines = events.map{e => new String(e.event.getBody().array(), "UTF-8")}