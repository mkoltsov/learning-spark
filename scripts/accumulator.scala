val file = sc.textFile("../CHANGES.txt")

val blankLines = sc.accumulator(0)

val callSigns = file.flatMap(
	line => {
			if (line==""){
				blankLines+=1
			}
		line.split(" ")
	})

callSigns.saveAsTextFile("output.txt")
println(s"Blank lines: $blankLines")