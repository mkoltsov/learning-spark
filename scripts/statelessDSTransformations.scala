dstream.map(x=>x+1)
dstream.flatMap(x=>x.split(" "))
dstream.filter(x=> x!=1)
dstream.repartition(10)
dstream.reduceByKey((x,y) => x+y)
dstream.groupByKey()

// Assumes ApacheAccessLog is a utility class for parsing entries from Apache logs
val accessLogDStream = logData.map(line => ApacheAccessLog.parseFromLogLine(line))
val ipDStream = accessLogsDStream.map(entry => (entry.getIpAddress(), 1))
val ipCountsDStream = ipDStream.reduceByKey((x, y) => x + y)

val ipBytesDStream = accessLogsDStream.map(entry => (entry.getIpAddress(), entry.getContentSize()))
val ipBytesSumDStream = ipBytesDStream.reduceByKey((x,y)=>x+y)
val ipBytesRequestCountsDStream = ipCountsDStream.join(ipBytesSumDStream)
// using StreamingContext.union() to join multiple streams is also possible

val outlierDStream = accessLogsDStream.transform{rdd => extractOutliers(rdd)}
// using these StreamingContext.transform or DStream.transformWith(otherStream, func) is also possible
// Setting up checkpointing
ssc.checkpoint("hdfs://...")