package fz.spark;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;

import scala.Tuple2;
public class spark {
	/**
     * @param args
     */
    public static void main(String[] args) {
    	try(final SparkSession spark = SparkSession
                .builder()
                .master("local")
                .appName("JavaLocalWordCount").getOrCreate()){
    		final List<String> content = Arrays.asList(
                    "Spark is a fast and general cluster computing system for Big Data. It provides",
                    "high-level APIs in Scala, Java, Python, and R, and an optimized engine that",
                    "supports general computation graphs for data analysis. It also supports a",
                    "rich set of higher-level tools including Spark SQL for SQL and DataFrames,",
                    "MLlib for machine learning, GraphX for graph processing,",
                    "and Spark Streaming for stream processing."
            );
    		@SuppressWarnings("resource")
            final Map<String, Long> wordsCount = new JavaSparkContext(spark.sparkContext())
                                    .parallelize(content)
                                    .flatMap((x) -> Arrays.asList(x.split(" ")).iterator())
                                    .mapToPair((x) -> new Tuple2<String, Integer>(x, 1))
                                    .countByKey();

            System.out.println(wordsCount);
    	}
                
       

            System.out.println("hello");
        }
    }

