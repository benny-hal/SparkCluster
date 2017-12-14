import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * Sample Spark application.
  */
object HelloWorld {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .appName("HelloWorld")
      .getOrCreate()

    println(spark)
    spark.stop()
  }

}
