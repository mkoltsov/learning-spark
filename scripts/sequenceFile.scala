//-------------- Reading sequenceFile
val data = sc.sequenceFile(inFile, classOf[Text], classOf[IntWritable]).
  map{case (x, y) => (x.toString, y.get())}
// Scala shortcut to get back an RDD of native Scala types
val data = sc.sequenceFile[Key, Value](path, minPartitions)


//-------------- Saving a sequenceFile
val data = sc.parallelize(List(("Panda", 3), ("Kay", 6), ("Snail", 2)))
data.saveAsSequenceFile(outputFile)

//Loading KeyValue Hadoop format
val input = sc.hadoopFile[Text, Text, KeyValueTextInputFormat](inputFile).map{
  case (x, y) => (x.toString, y.toString)
}

//Loading LZO-compressed JSON with Elephant Bird in Scala
val input = sc.newAPIHadoopFile(inputFile, classOf[LzoJsonInputFormat],
  classOf[LongWritable], classOf[MapWritable], conf)
// Each MapWritable in "input" represents a JSON object

//rest of Hadoop-related methods

sc.hadoopFile()
rdd.saveAsHadoopFile()
sc.hadoopDataset
rdd.saveAsHadoopDataSet 
sc.newAPIHadoopDataset
rdd.saveAsNewAPIHadoopDataset