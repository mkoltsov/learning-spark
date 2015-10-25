val rdd1 = sc.parallelize(List(1,2,3,4,5))

println(rdd.reduce((x,y) => x+y))