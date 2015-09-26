import java.io.StringReader
import au.com.bytecode.opencsv.CSVReader
//READING CSV
val input = sc.textFile("1.csv")
val result = input.map{ line =>
  val reader = new CSVReader(new StringReader(line));
  reader.readNext();
}

case class Person(name: String, favoriteAnimal: String)

val input = sc.wholeTextFiles("1.csv")
val result = input.flatMap{ case (_, txt) =>
  val reader = new CSVReader(new StringReader(txt));
  reader.readAll().map(x => Person(x(0), x(1)))
}
//WRITING CSV
pandaLovers.map(person => List(person.name, person.favoriteAnimal).toArray)
.mapPartitions{people =>
  val stringWriter = new StringWriter();
  val csvWriter = new CSVWriter(stringWriter);
  csvWriter.writeAll(people.toList)
  Iterator(stringWriter.toString)
}.saveAsTextFile(outFile)
