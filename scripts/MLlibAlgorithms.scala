//---------------TF-IDF
// Using HashingTF in Python
>>> from pyspark.mllib.feature import HashingTF

>>> sentence = "hello hello world"
>>> words = sentence.split()  # Split sentence into a list of terms
>>> tf = HashingTF(10000)  # Create vectors of size S = 10,000
>>> tf.transform(words)
SparseVector(10000, {3065: 1.0, 6861: 2.0})

>>> rdd = sc.wholeTextFiles("data").map(lambda (name, text): text.split())
>>> tfVectors = tf.transform(rdd)   # Transforms an entire RDD

// Using TF-IDF in Python
from pyspark.mllib.feature import HashingTF, IDF

# Read a set of text files as TF vectors
rdd = sc.wholeTextFiles("data").map(lambda (name, text): text.split())
tf = HashingTF()
tfVectors = tf.transform(rdd).cache()

# Compute the IDF, then the TF-IDF vectors
idf = IDF()
idfModel = idf.fit(tfVectors)
tfIdfVectors = idfModel.transform(tfVectors)
//---------SCALING
from pyspark.mllib.feature import StandardScaler

vectors = [Vectors.dense([-2.0, 5.0, 1.0]), Vectors.dense([2.0, 0.0, 1.0])]
dataset = sc.parallelize(vectors)
scaler = StandardScaler(withMean=True, withStd=True)
model = scaler.fit(dataset)
result = model.transform(dataset)

//------------WORD2VEC
 Word2Vec.fit(rdd)
 //------------STATISTICS
 Statistics.colStats(rdd)
// Computes a statistical summary of an RDD of vectors, which stores the min, max, mean, and variance for each column in the set of vectors. This can be used to obtain a wide variety of statistics in one pass.
Statistics.corr(rdd, method)
// Computes the correlation matrix between columns in an RDD of vectors, using either the Pearson or Spearman correlation (method must be one of pearson and spearman).
Statistics.corr(rdd1, rdd2, method)
// Computes the correlation between two RDDs of floating-point values, using either the Pearson or Spearman correlation (method must be one of pearson and spearman).
Statistics.chiSqTest(rdd)
// Computes Pearson’s independence test for every feature with the label on an RDD of LabeledPoint objects. Returns an array of ChiSqTestResult objects that capture the p-value, test statistic, and degrees of freedom for each feature. Label and feature values must be categorical (i.e., discrete values).

//REGRESSION & Classification
// Linear regression
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.regression.LinearRegressionWithSGD

val points: RDD[LabeledPoint] = // ...
val lr = new LinearRegressionWithSGD().setNumIterations(200).setIntercept(true)
val model = lr.run(points)
println("weights: %s, intercept: %s".format(model.weights, model.intercept))

// Logistic regression
// use mllib.classification.LogisticRegressionWithLBFGS and WithSGD classes

//------ Support Vector Machines
// Naive Bayes
// use mllib.classification.NaiveBayes for that and the apply predict()

// ---------------Decision trees and random forests
// use mllib.tree.DecisionTree
// and RandomForest.trainClassifier and trainRegressor

//K-MEANS
// use mllib.clustering.KMeans

// Alternating Least Squares
// use the mllib.recommendation.ALS

// Dimensionality Reduction
// Principal component analysis

// PCA in Scala
import org.apache.spark.mllib.linalg.Matrix
import org.apache.spark.mllib.linalg.distributed.RowMatrix

val points: RDD[Vector] = // ...
val mat: RowMatrix = new RowMatrix(points)
val pc: Matrix = mat.computePrincipalComponents(2)

// Project points to low-dimensional space
val projected = mat.multiply(pc).rows

// Train a k-means model on the projected 2-dimensional data
val model = KMeans.train(projected, 10)

// SVD in Scala
// Compute the top 20 singular values of a RowMatrix mat and their singular vectors.
val svd: SingularValueDecomposition[RowMatrix, Matrix] =
  mat.computeSVD(20, computeU=true)

val U: RowMatrix = svd.U  // U is a distributed RowMatrix.
val s: Vector = svd.s     // Singular values are a local dense vector.
val V: Matrix = svd.V     // V is a local dense matrix.

// Model Evaluation
// use mllib.evaluation package, BinaryClassificationMetrics and MulticlassMetrics are the perfect classes for this job