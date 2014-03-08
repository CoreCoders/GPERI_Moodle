package com.example.gperimoodle;

public class Content
{
	
	private static String[] fileName,fileURL,fileSize;

	public static String[] getFileName() {
		return fileName;
	}

	public static void setFileName(String[] fileName) {
		Content.fileName = fileName;
	}

	public static String[] getFileURL() {
		return fileURL;
	}

	public static void setFileURL(String[] fileURL) {
		Content.fileURL = fileURL;
	}

	public static String[] getFileSize() {
		return fileSize;
	}

	public static void setFileSize(String[] fileSize) {
		Content.fileSize = fileSize;
	}

}
