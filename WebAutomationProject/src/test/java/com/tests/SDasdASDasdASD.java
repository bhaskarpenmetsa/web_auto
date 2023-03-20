package com.tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class SDasdASDasdASD {
	
	public static String fpath;

	public static void main(String[] args) throws IOException {

	
		
		
		
		
		// TODO Auto-generated method stub
		File directoryPath = new File("D:\\isecdummy");
		// List of all files and directories
		File filesList[] = directoryPath.listFiles();
		System.out.println("List of files and directories in the specified directory:");
		for (File file : filesList) {
			System.out.println("File name: " + file.getName());
			
			fpath=file.getAbsolutePath().toString();
			System.out.println("File path: " + fpath);
			System.out.println("Size :" + file.getTotalSpace());
			
			File directory = new File(fpath);
			FileUtils.deleteDirectory(directory);
			System.out.println(" ");

		}
		
	}
}