val input = sc.textFile("input.txt")

val processed = input.filter(x => x.length>0).map(x=>x.split(" "))

val counts = processed.map(words=> (words(0), 1)).reduceByKey{(a,b) => a+b}

println(input.toDebugString)
println(counts.toDebugString)