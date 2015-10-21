val input = sc.parallelize(Array(1,2,3,4,5,6))
val input2 = sc.parallelize(List(1,2,3,4,5,6))

val result = input.map(x => x * x)
val result2 = input2.map(x => x * x)
println(result.collect().mkString(","))
println(result2.collect().mkString(","))

val someInput = sc.parallelize(List("some words", "hello", " world"))

val flat = someInput.flatMap(x => x.split(" "))

println(flat.first())
