####HelloWorld Example
```shell script
spark-submit --master local  --class learning.spark.HelloWorld ./target/scala-2.11/scala-demo_2.11-0.1.jar 
```

####File Formats Example
The `spark-avro` module is external and not included in `spark-submit` or `spark-shell` by default.

As with any Spark applications, `spark-submit` is used to launch your application. `spark-avro_2.11` and its dependencies can be directly added to `spark-submit` using --packages, such as,
```shell script
spark-submit --master local  --packages org.apache.spark:spark-avro_2.11:2.4.0 --class learning.spark.BigDataFileFormats ./target/scala-2.11/scala-demo_2.11-0.1.jar
```

####WordCount Example using RDD's
```shell script
spark-submit --master local  --class learning.spark.rdd.WordCount ./target/scala-2.11/scala-demo_2.11-0.1.jar ./ReadMe.md ./counts
```
