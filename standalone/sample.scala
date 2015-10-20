import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._ 
val conf = new SparkConf().setMaster("local").setAppName("My App")
val sc = new SparkContext(conf)

// Load our input data.
val input =  sc.parallelize(Array(1,2,3,4))
// Split it up into words.
//val words = input.flatMap(line => line.split(" "))
// Transform into pairs and count.
//val counts = words.map(word => (word, 1)).reduceByKey{case (x, y) => x + y}
// Save the word count back out to a text file, causing evaluation.
input.saveAsTextFile("1.txt")

// Create a Scala Spark Context.
// val conf = new SparkConf().setAppName("wordCount")
// val sc = new SparkContext(conf)
// // Load our input data.
// val input =  sc.textFile(inputFile)
// // Split it up into words.
// val words = input.flatMap(line => line.split(" "))
// // Transform into pairs and count.
// val counts = words.map(word => (word, 1)).reduceByKey{case (x, y) => x + y}
// // Save the word count back out to a text file, causing evaluation.
// counts.saveAsTextFile(outputFile)