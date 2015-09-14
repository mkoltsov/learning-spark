val signPrefixes = sc.broadcast(loadCallSignTable())

val countryContactCounts = contactCounts.map
	{
		case (sign, count) => 
		val country = lookUpInArray(sign, signPrefixes.value)
		(country, count)
	}.reduceByKey((x,y) => x+y)

countryContactCounts.saveAsTextFile(s"$outputDir/countries.txt")	

