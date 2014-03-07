package com.example.gperimoodle;

import java.util.regex.*;


public class ParseXML
{
	static String xml,key,value,newData;
	static int keyIndex,keyLength;
	static int start,end;
	
	public static String getValue(String k,String data)
	{
		
		xml=data;
		key=k;
		keyLength=key.length();
		keyIndex=xml.indexOf(key);
		
		start=keyIndex+keyLength+9;	
		
		end=xml.indexOf("</KEY>", keyIndex);
		
		value=xml.substring(start,end);
		
		if(!value.contains("null"))
		{

			value=value.substring(0, value.indexOf("</VALUE>"));
			return value;
			
		}
		
		return value="N/A";
		
	}
	
	
	public static Integer countX(String x, String data)
	{
		Integer countX=new Integer(0);
		Pattern key=Pattern.compile(x);
		Matcher matcher=key.matcher(data);
		
		while(matcher.find())
		{
			countX=countX+1;
		}
		
		return countX;
	}
	
	
	public static String[] getSingleDetails(String tag1,String tag2,String data)
	{
		String[] x=new String[2];
		
		start=data.indexOf(tag1);
		end=data.indexOf(tag2)+tag2.length();
		
		newData=data.substring(start, end);
		start=end;
		data=data.substring(start);
		start=data.indexOf("<SINGLE>");
		end=data.lastIndexOf("</SINGLE>");
		
		x[0]=newData;
		x[1]=data;
		
		return x;
		 
	}
	

}
