package com.yd.ydbi.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ZipCount {

	public static void main(String[] args) {
		File baseFolder = new File(BASE_DIR);
		System.out.print("Process logfile:" + baseFolder.getName() + ".");
		processLogFile(baseFolder);
		System.out.println("End process!");
	}

	/**
	 * processLogFile TODO 解析日志文件，生成insert语句并写入到insert.sql文件中
	 */
	private static void processLogFile(File f) {
		// TODO Auto-generated method stub
		BufferedReader br = null;
		String readLine;
		BufferedWriter bw = null;

		try {
			br = new BufferedReader(new FileReader(f));
			bw = new BufferedWriter(new FileWriter(DEST_FILE, true));
			while ((readLine = br.readLine()) != null) {
//				if (readLine.startsWith(ACCESS_LOG_PREFIX)) {
					String[] accessInfo = readLine.split(ACCESS_LOG_DELIMITER);
					StringBuilder sb = new StringBuilder();
					if(accessInfo.length == 4){
						sb.append(String.format(INSERT_ALLFILL, accessInfo[1], accessInfo[2], accessInfo[3]));
						sb.append('\n');
					}
					/*try {
						sb.append(String.format(INSERT_PATTERN, accessInfo[1], accessInfo[2], accessInfo[3],accessInfo[4],accessInfo[5]));
						sb.append('\n');
					} catch (Exception e) {
						System.out.println(readLine);
						e.printStackTrace();
					}*/
					
					// sb 内容写入目标文件
					bw.write(sb.toString());
					bw.flush();
//				}
			}

//			// sb 内容写入目标文件
//			bw.write(sb.toString());
//			bw.flush();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static final String BASE_DIR = "/u01/a.txt";
	public static final String DEST_FILE = "/u01/insert_bigfile.sql";

	public static final String ACCESS_LOG_PREFIX = "AccessLog";
	public static final String ACCESS_LOG_DELIMITER = "/";
	public static final String INSERT_PATTERN = "INSERT INTO zip_count_log (dirStr, dataexp, orgcd, dt,filename) VALUES ('%1$s', '%2$s', '%3$s', '%4$s', '%5$s');";
	public static final String INSERT_ALLFILL = "INSERT INTO zip_bigfile (dirStr, dataexp, filename) VALUES ('%1$s', '%2$s', '%3$s');";

}
