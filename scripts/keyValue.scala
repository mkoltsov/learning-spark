val lines = sc.parallelize(List("ch ef", "pu pa", "pa pa"))

val pairs = lines.map(x => (x.split(" ")(0), x))

println(pairs.collect().mkString(","))
sc.parallelize(List(("chef", "pupa"), ("papa", "bupa"))).collect().foreach(println)

val example = sc.parallelize(List((1,2), (3,4),(3,6)))

println(example.reduceByKey((x,y) => x+y).foreach(println))

