def createConnection() = {
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	DriverManager.getConnection("jdbc:mysql://localhost/test?user=holden");
}

def extractValues(r:ResultSet) = {
	(r.getInt(1), r.getString(2))
}

val data = new JdbcRDD(sc, createConnection, "select * from panda WHERE ? <= id AND id <=?", lowerBound=1,
upperBound=3, numPartitions=2, mapRow=extractValues)

println(data.collect().toList)