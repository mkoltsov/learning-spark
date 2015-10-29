val lines = sc.parallelize(List("ch ef", "pu pa", "pa pa"))

val pairs = lines.map(x => (x.split(" ")(0), x))

println(pairs.collect().mkString(","))
sc.parallelize(List(("chef", "pupa"), ("papa", "bupa"))).collect().foreach(println)

