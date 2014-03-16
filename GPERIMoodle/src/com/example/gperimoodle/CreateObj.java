package com.example.gperimoodle;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.hardware.Camera.Size;


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
	
	
	public void createContentObj(Context context,String courseId)
	{
		
		List<String> fileName=new ArrayList<String>();
		List<String> fileURL=new ArrayList<String>();
		List<String> fileType=new ArrayList<String>();
		List<String> fileSize=new ArrayList<String>();
		
		try
		{
			
			Integer count;
			count=Integer.valueOf(0);
			
			String[] fName;
			String[] fURL;
			String[] fType;
			String[] fSize;
			
			this.context=context;
			
			String[] result=new String[2];
			String[] result2=new String[2];
		
			User.setResponseXML(new Moodle(context).execute("6",User.getToken(),courseId).get());			
			
			result=ParseXML.getSingleDetails("<KEY name=\"contents\"><MULTIPLE><SINGLE>", "</SINGLE></MULTIPLE></KEY>", User.getResponseXML());
			
			while(result[1].contains("<KEY name=\"contents\"><MULTIPLE><SINGLE>"))
			{
			
			//result.setText(retResult[0]);
				
				result=ParseXML.getSingleDetails("<KEY name=\"contents\"><MULTIPLE><SINGLE>", "</SINGLE></MULTIPLE></KEY>", User.getResponseXML());
			
				count=ParseXML.countX("<SINGLE>", result[0]);
				
				for(int i=0;i<count;i++)
				{
					result2=ParseXML.getSingleDetails("<SINGLE>", "</SINGLE>", result[0]);
					fileName.add(ParseXML.getValue("filename", result2[0]));
					fileURL.add(ParseXML.getValue("fileurl", result2[0]));
					fileSize.add(ParseXML.getValue("filesize", result2[0]));
					fileType.add(ParseXML.getValue("type", result2[0]));
					
					result[0]=result2[1];
					
				}
				
				
				User.setResponseXML(result[1]);
			
			}
			
			fName=fileName.toArray(new String[fileName.size()]);
			fURL=fileURL.toArray(new String[fileURL.size()]);
			fSize=fileSize.toArray(new String[fileSize.size()]);
			fType=fileType.toArray(new String[fileType.size()]);
			
			Content.setFileName(fName);
			Content.setFileURL(fURL);
			Content.setFileSize(fSize);
			Content.setFileType(fType);
		
		}
		catch (Exception e)
		{
			
		}
		
	}
	
		
		
		
	
	
	

}
