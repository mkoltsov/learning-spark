val input = sc.wholeTextFiles("../")
val a = input.collectAsMap()
println(a.keys.reduce((x,y)=>x+";"+y))