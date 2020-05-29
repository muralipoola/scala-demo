####HelloWorld Example
```shell script
spark-submit --master local  --class learning.spark.HelloWorld ./target/scala-2.11/scala-demo_2.11-0.1.jar 
```

####File Formats Example
```shell script
spark-submit --master local  --class learning.spark.BigDataFileFormats ./target/scala-2.11/scala-demo_2.11-0.1.jar 
```

####WordCount Example using RDD's
```shell script
spark-submit --master local  --class learning.spark.rdd.WordCount ./target/scala-2.11/scala-demo_2.11-0.1.jar ./ReadMe.md ./counts
```
