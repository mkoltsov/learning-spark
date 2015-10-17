val rdd1 = sc.parallelize(List(1,1,2,3,4,5))

println(rdd1.reduce((x,y) => x+y))

println(rdd1.fold(-1)((x,y) => x+y))

//val result = rdd1.aggregate((0,0))((acc, value) => (acc._1 + value, acc._2 +1),
//	(acc1, acc2) => (acc1._1 + acc2._1, acc1_.2 + acc2._2))
//println (result._1/ result._2.toDouble)

println(rdd1.top(3).mkString(","))
println(rdd1.takeSample(true, 3, 0).mkString(","))
rdd1.foreach(println)
println(rdd1.count())
println(rdd1.countByValue())
println(rdd1.mean())
println(rdd1.variance())
//println(rdd1.takeOrdered(3)((x:Int, y:Int) => (x<y)))

println("PERSISTANCE")

val result = rdd1.map(x=>x*x)
result.persist(StorageLevel.DISK_ONLY)
result.unpersist()
println(result.count())
println(result.collect().mkString(","))