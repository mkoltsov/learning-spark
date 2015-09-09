val distanceDouble = sc.parallelize(List("1","2", "43", "54", "57")).map(string => string.toDouble)

val stats = distanceDouble.stats()

val stddev = stats.stdev
val mean = stats.mean
val reasonableDistance = distanceDouble.filter(x => math.abs(x-mean)<3 * stddev)

println(reasonableDistance.collect().toList)