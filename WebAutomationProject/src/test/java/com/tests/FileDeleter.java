package com.tests;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;

public class FileDeleter {
	public static String fpath;

	public static void main(String[] args) throws IOException, ParseException {

		File directory = new File("D:\\isecdummy\\Screenshots");

		LocalDate todayDate = LocalDate.now();
		System.out.println("Current date: " + todayDate);

		LocalDate yesterday = LocalDate.now();
		System.out.println("yesterday  Date: " + yesterday.minusDays(1));

		File[] files = directory.listFiles();

		for (File file : files) {

			long lastModified = file.lastModified();

			Date fileDate = new Date(lastModified);

			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(fileDate);
			System.out.println(date);

			if (!date.equals(todayDate.toString()) && !date.equals(yesterday.minusDays(1).toString())) {

				fpath = file.getAbsolutePath().toString();
				System.out.println("File path: " + fpath);
				File directory1 = new File(fpath);
				FileUtils.deleteDirectory(directory1);

				System.out.println("dELETED........................");
			} else {

				System.out.println("Today date");
			}
		}
	}

}
