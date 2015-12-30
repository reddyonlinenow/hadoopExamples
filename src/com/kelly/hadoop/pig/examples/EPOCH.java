package com.kelly.hadoop.pig.examples;

import java.io.IOException;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;
import org.apache.pig.impl.util.WrappedIOException;

public class EPOCH extends EvalFunc<String>

{
	public String exec(Tuple input) throws IOException {
		if (input == null || input.size() == 0)
			return null;
		try{
			String str = (String)input.get(0);
			String date = new java.text.SimpleDateFormat("yyyy-MM-dd:HH").format(new java.util.Date (Integer.parseInt(str)*1000));
			return date;
		}catch(Exception e){
			throw WrappedIOException.wrap("Caught exception processing input row ", e);
		}
	}
}

