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