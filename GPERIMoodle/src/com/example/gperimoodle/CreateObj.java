package com.example.gperimoodle;

import android.content.Context;


public class CreateObj
{
	
	Context context;
	
	public void createCourseObj(Context context)
	{
		
		
		
		try
		{
			
			this.context=context;
		
		String[] result=new String[2];
		
		Integer subCount;
		
		subCount=Integer.valueOf(0);
		
		User.setResponseXML(new Moodle(context).execute("3",User.getToken(),User.getId()).get());
		
		String[] id,sName,fName;		
		
		subCount=ParseXML.countX("<SINGLE>",User.getResponseXML());
		
		//courseFullName=new String[subCount];
		id=new String[subCount];
		sName=new String[subCount];
		fName=new String[subCount];
				
		for(int i=0;i<subCount;i++)
		{
			
			result=ParseXML.getSingleDetails("<SINGLE>","</SINGLE>",User.getResponseXML());

			id[i]=ParseXML.getValue("id", result[0]);
			sName[i]=ParseXML.getValue("shortname", result[0]);
			fName[i]=ParseXML.getValue("fullname", result[0]);
					
			User.setResponseXML(result[1]);
			
		}
		
		Course.setId(id);
		Course.setfName(fName);
		Course.setsName(sName);
		Course.setTotalCourses(subCount);
		
		
	}
	catch(Exception e)
	{
		
	}
		
			
	}
	
	public void createFriendObj(Context context)
	{
		
		try
		{
			
			this.context=context;
			
			Integer countFriends;
			countFriends=Integer.valueOf(0);
			
			String[] courses;
			
			courses=Course.getId();
			
			String[] id,name;
			
			
			String[] result=new String[2];
			
			User.setResponseXML(new Moodle(context).execute("4",User.getToken(),courses[0]).get());
			
			
			countFriends=ParseXML.countX("<SINGLE>", User.getResponseXML());
			
			id=new String[countFriends];
			name=new String[countFriends];
			
			for(int i=0;i<countFriends;i++)
			{
				result=ParseXML.getSingleDetails("<SINGLE>","</SINGLE>",User.getResponseXML());
				id[i]=ParseXML.getValue("userid", result[0]);
				name[i]=ParseXML.getValue("fullname", result[0]);
				name[i]=name[i].substring(name[i].indexOf('_')+1);
				
				User.setResponseXML(result[1]);
			}
			
			Friend.setId(id);
			Friend.setName(name);
			Friend.setTotalFriends(countFriends);
			
			
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
		
		
	}
	
		
		
		
	
	
	

}
