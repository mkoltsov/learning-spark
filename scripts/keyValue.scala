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


