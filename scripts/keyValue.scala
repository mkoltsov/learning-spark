val lines = sc.parallelize(List("ch ef", "pu pa", "pa pa"))

val pairs = lines.map(x => (x.split(" ")(0), x))

println(pairs.collect().mkString(","))
sc.parallelize(List(("chef", "pupa"), ("papa", "bupa"))).collect().foreach(println)

val example = sc.parallelize(List((1,2), (3,4),(3,6)))

println(example.reduceByKey((x,y) => x+y).foreach(println))
println(example.groupByKey().foreach(println))
// println(example.combineByKey().foreach(println))
println(example.mapValues(x => x+1).foreach(println))
println("flatMapValues")
println(example.flatMapValues(x => (x to 5)).foreach(println))
//println(example.keys())
//println(example.values())
println(example.sortByKey().foreach(println))

println("transformation on two pairs")

val rdd1 = sc.parallelize(List((1,2), (3,4), (3,6)))
val other = sc.parallelize(List((3,9)))

println(rdd1.subtractByKey(other).foreach(println))
println("joining two RDDs")
println(rdd1.join(other).foreach(println))
//println(rdd1.rightOuterjoin(other).foreach(println))
//println(rdd1.leftOuterjoin(other).foreach(println))
println(rdd1.cogroup(other).foreach(println))
println("filter upon a pair")

rdd1.filter{case (key, value) => value > 3}.foreach(println)

rdd1.mapValues(x => (x,1)).foreach(println)
println("reducing")
rdd1.mapValues(x => (x,1)).reduceByKey((x,y) => (x._1+y._1, x._2 + y._2)).foreach(println)

println("word count")

println("fast")

val input = sc.textFile("../CHANGES.txt")

println(input.flatMap(x => x.split(" ")).countByValue())

println("steady")

println(input.flatMap(x => x.split(" ")).map(x => (x, 1)).reduceByKey((x, y) => x+y))

println("combine by key")

val result = rdd1.combineByKey(
	(v) => (v,1),
	(acc: (Int, Int), v) => (acc._1 + v, acc._2+1),
	(acc1: (Int, Int),acc2:(Int, Int)) => (acc1._1 + acc2._1, acc1._2 + acc2._2)
	).map{case (key, value) => (key, value._1/value._2.toFloat) }
result.collectAsMap().map(println(_))

val data = Seq(("a", 3), ("b", 4), ("a", 1))
sc.parallelize(data).reduceByKey((x, y) => x + y)    // Default parallelism
sc.parallelize(data).reduceByKey((x, y) => x + y, 10)    // Custom parallelism


val repart = rdd1.repartition(20)
println(repart.partitions.size)
val coalesced = rdd1.coalesce(20, true)
println(coalesced.partitions.size)
println("grouping")
println(rdd1.groupByKey().collect().mkString(","))
println(rdd1.reduceByKey((x,y) => x+y).collect().mkString(","))
println(rdd1.groupByKey().mapValues(value => value.reduce((x,y) => x+y)).collect().mkString(","))

println(lines.groupBy(x => x.contains("p")).collect().mkString(","))
println("joining")
println(rdd1.cogroup(other).collect().mkString(","))
println(rdd1.join(other).foreach(println))
println("available with actions")
println(rdd1.countByKey())
println(rdd1.collectAsMap())
println(rdd1.lookup(3))