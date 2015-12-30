package com.kelly.hadoop.hbase.examples;

import java.io.IOException;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;


public class HBASEExample {

	private static HBaseAdmin admin;

	public static void main(String[] args) {

		Configuration conf = HBaseConfiguration.create();
		 conf.set("hbase.rootdir", "hdfs://localhost.localdomain:8020/hbase");
//		conf.set("hbase.rootdir", "/var/hbase");

		//conf.set("hbase.zookeeper.quorum", "localhost");
//		conf.set("hbase.zookeeper.property.dataDir", "/var/zookeeper");
		try {
			admin = new HBaseAdmin(conf);

			HTableDescriptor htab = new HTableDescriptor("Test_"+System.currentTimeMillis());
			HColumnDescriptor hcolfam = new HColumnDescriptor("COLFAM1");
			htab.addFamily(hcolfam);
			admin.createTable(htab);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
