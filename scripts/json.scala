import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.DeserializationFeature

case class Person(name: String, lovesPandas: Boolean) // Must be a top-level class

val input = "{'name':'chef', 'lovesPandas':true}"
// Parse it into a specific case class. We use flatMap to handle errors
// by returning an empty list (None) if we encounter an issue and a
// list with one element if everything is ok (Some(_)).
// val mapper = new ObjectMapper() with ScalaObjectMapper
// mapper.registerModule(DefaultScalaModule)

// val result = input.flatMap(record => {
//   try {
//     Some(mapper.readValue(record, classOf[Person]))
//   } catch {
//     case e: Exception => None
//   }})