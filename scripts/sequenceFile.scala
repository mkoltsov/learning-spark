//-------------- Reading sequenceFile
val data = sc.sequenceFile(inFile, classOf[Text], classOf[IntWritable]).
  map{case (x, y) => (x.toString, y.get())}
// Scala shortcut to get back an RDD of native Scala types
val data = sc.sequenceFile[Key, Value](path, minPartitions)
