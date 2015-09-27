import java.io.StringReader
import au.com.bytecode.opencsv.CSVReader

val input = sc.textFile("1.csv")
val result = input.map{ line =>
  val reader = new CSVReader(new StringReader(line));
  reader.readNext();
}