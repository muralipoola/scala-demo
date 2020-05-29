package learning.spark

import java.time.format.DateTimeFormatter

import org.apache.spark.sql.{DataFrame, SparkSession}

object BigDataFileFormats {

  val outputDirectory = "./target/output/"

  def main(args: Array[String]): Unit = {

    val spark: SparkSession = getSparkSession

    val df = readCsvFile(spark, "./src/main/resources/diamonds.csv")
    displayDataFrame(df)

    df.write.csv(getUniqueFileName("diamonds-csv-1-"))

    df.write.json(getUniqueFileName("diamonds-json-"))

    df.write.format("avro").save(getUniqueFileName("diamonds-avro-"))

    val parquetFilePath = getUniqueFileName("diamonds-parquet-")
    df.write.parquet(parquetFilePath)

    val df_parquet = spark.read.parquet(parquetFilePath)
    displayDataFrame(df_parquet)
    df_parquet.write.csv(getUniqueFileName("diamonds-csv-2-"))

    spark.close()
  }

  private def getUniqueFileName(prefix: String): String = {
    outputDirectory + prefix +
      DateTimeFormatter.ofPattern("dd-MM-YYYY hh-mm-ss").format(java.time.LocalDateTime.now)
  }

  private def displayDataFrame(df: DataFrame) = {
    println("===============================================")
    println(s"Read ${df.count()} records from CSV file...")
    println("Schema:")
    df.printSchema()
    println("Data:")
    df.show(false)
    println("===============================================")
  }

  private def getSparkSession: SparkSession = {
    val spark = SparkSession.builder()
      .appName("BigDataFileFormats")
      .config("spark.eventLog.enabled", false)
      .getOrCreate();
    spark
  }

  private def readCsvFile(sparkSession: SparkSession, filePath: String): DataFrame = {
    sparkSession
      .read
      .option("header", true)
      .option("inferSchema", true)
      .csv(filePath)
      .na.drop()
  }

}
