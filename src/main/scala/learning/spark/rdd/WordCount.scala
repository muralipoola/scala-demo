package learning.spark.rdd

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    // Create a Scala Spark Context.
    val conf = new SparkConf().setAppName("wordCount")
    val sc = new SparkContext(conf)

    if (args.length < 2) {
      println("Invalid number of arguments.")
      println("Correct Usage: \n\t<<program.jar>> <<inputFile>> <<outputFile>>")
      sys.exit()
    }

    val inputFile = args(0)
    val outputFile = args(1)

    println(s"inputFile: $inputFile")
    println(s"outputFile: $outputFile")

    // Load input data
    val lines = sc.textFile(inputFile)

    // Split it up into words.
    val words = lines.flatMap(line => line.split(" "))

    // Transform into pairs and count.
    val counts = words.map(word => (word, 1)).reduceByKey { case (x, y) => x + y }

    // Save the word count back out to a text file, causing evaluation.
    counts.saveAsTextFile(outputFile)
  }
}
