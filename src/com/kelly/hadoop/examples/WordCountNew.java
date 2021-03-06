package com.kelly.hadoop.examples;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class WordCountNew {

	public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {

		public TokenizerMapper() {
			System.out.println("Mapper code starts here");
		}

		private static final IntWritable one = new IntWritable(1);
		private Text word = new Text();

		// Below method we have to override to write Mapper Business logic
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

			// Input Data -- hadoop is bigdata tool
			StringTokenizer st = new StringTokenizer(value.toString());
			// Output-->Null
			// Hadoop
			// is
			// BigData
			// Tool

			while (st.hasMoreTokens()) {
				String str = (String) st.nextToken();
				word.set(str);
				System.out.println("Word is -->" + word);
				context.write(word, one);
			}
		}
	}

	public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
		IntWritable iw = new IntWritable();

		public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException,
				InterruptedException {
			int sum = 0;

			for (IntWritable val : values) {
				sum += val.get();
			}
			iw.set(sum);
			context.write(key, iw);
		}
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

		System.out.println("Main method execution started at " + System.currentTimeMillis());
		Configuration conf = new Configuration();
		
		
		Job job = new Job(conf, "WordCOUNTJOB");
		job.setJarByClass(WordCountNew.class);

		// Mapper, Combiner & Reducer class name details

		job.setMapperClass(TokenizerMapper.class);

		
		// job.setNumReduceTasks(0);

		job.setCombinerClass(IntSumReducer.class);
		job.setReducerClass(IntSumReducer.class);

		// Final output Key, value data type details
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		// setting the input format and output format
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		// HDFS input path, HDFS output path details

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		// System exit process
		System.exit(job.waitForCompletion(true) ? 0 : 1);

	}
}
