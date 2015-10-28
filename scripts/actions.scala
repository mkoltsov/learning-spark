val rdd1 = sc.parallelize(List(1,2,3,4,5))

println(rdd1.reduce((x,y) => x+y))

println(rdd1.fold(-1)((x,y) => x+y))