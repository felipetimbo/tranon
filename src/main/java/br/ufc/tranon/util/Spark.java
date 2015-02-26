package br.ufc.tranon.util;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

public class Spark {

	 public static void main(String[] args) {
		 
		 SparkConf conf = new SparkConf().setAppName("wordCount").setMaster("local");
		 JavaSparkContext sc = new JavaSparkContext(conf);

		 JavaRDD<String> input = sc.textFile("file://C:/in.txt");

		 JavaRDD<String> words = input.flatMap(
				 new FlatMapFunction<String, String>() {
					 public Iterable<String> call(String x) {
						 return Arrays.asList(x.split(" "));
					 }});
		 // Transform into pairs and count.
		 JavaPairRDD<String, Integer> counts = words.mapToPair(
				 new PairFunction<String, String, Integer>(){
					 public Tuple2<String, Integer> call(String x){
						 return new Tuple2(x, 1);
					 }}).reduceByKey(new Function2<Integer, Integer, Integer>(){
						 public Integer call(Integer x, Integer y){ return x + y;}});
		 // Save the word count back out to a text file, causing evaluation.
		 System.out.println(counts.count());
		 
	}
	
}
