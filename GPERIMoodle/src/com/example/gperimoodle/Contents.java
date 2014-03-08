package com.example.gperimoodle;

public class Contents
{
	
	private static String[] fileName,fileURL,fileSize;

	public static String[] getFileName() {
		return fileName;
	}

	public static void setFileName(String[] fileName) {
		Contents.fileName = fileName;
	}

	public static String[] getFileURL() {
		return fileURL;
	}

	public static void setFileURL(String[] fileURL) {
		Contents.fileURL = fileURL;
	}

	public static String[] getFileSize() {
		return fileSize;
	}

	public static void setFileSize(String[] fileSize) {
		Contents.fileSize = fileSize;
	}

}
