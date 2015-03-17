package br.ufc.tranon.util;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;

public class Spark {

	 private static JavaSparkContext sc;

	public static void main(String[] args) {
		 
		 SparkConf conf = new SparkConf().setAppName("wordCount").setMaster("local");
		 sc = new JavaSparkContext(conf);

		 JavaRDD<String> input1 = sc.textFile("file://C:/2978-Nodes.txt");
		 JavaRDD<String> input2 = sc.textFile("file://C:/in2.txt");

		 JavaRDD<String> words1 = input1.flatMap(
				 new FlatMapFunction<String, String>() {
					private static final long serialVersionUID = 1L;

					public Iterable<String> call(String x) {
						 return Arrays.asList(x.split(" "));
					 }});
		 
		 JavaRDD<String> words2 = input2.flatMap(
				 new FlatMapFunction<String, String>() {

					private static final long serialVersionUID = -8047697260384518562L;

					public Iterable<String> call(String x) {
						 return Arrays.asList(x.split(" "));
					 }});
		 
//		 // Transform into pairs and count.
//		 
//		 JavaPairRDD<String, Integer> counts = words.mapToPair(
//				 new PairFunction<String, String, Integer>(){
//					 public Tuple2<String, Integer> call(String x){
//						 return new Tuple2(x, 1);
//					 }}).reduceByKey(new Function2<Integer, Integer, Integer>(){
//						 public Integer call(Integer x, Integer y){ return x + y;}});
		 
		 
//		 JavaRDD<String> filter = words1.filter(new Function<String, Boolean>() {
//			 public Boolean call(String x) { 
////				 return x.
//			 }
//		 });
		 
//		 for(int i=1; i <= 3; i++){
			 System.out.println(words1.cartesian(words1.cartesian(words1)).count());
//		 }
		 
//		 System.out.println(filter.collect());
		 
	}
	
}
