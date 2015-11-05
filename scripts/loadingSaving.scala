val input = sc.wholeTextFiles("../")
val a = input.collectAsMap()
println(a.keys.reduce((x,y)=>x+";"+y))

val result = input.mapValues{y => 
val nums = y.split(" ").map(x=>x.toDouble)
nums.sum/nums.size.toDouble}