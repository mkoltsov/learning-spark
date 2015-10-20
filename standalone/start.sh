git clone git@github.com:databricks/learning-spark.git
cd learning-spark
sbt clean package
$SPARK_HOME/bin/spark-submit --class com.oreilly.learningsparkexamples.mini.scala.WordCount ./learning-spark-mini-example_2.10-0.0.1.jar ../../../ ./wordcounts