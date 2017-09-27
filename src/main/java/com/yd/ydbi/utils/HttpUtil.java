package com.yd.ydbi.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import org.apache.log4j.Logger;


/**
 * @author kangyuanjia
 * @createTime 2017-03-22 01:10:45
 * @describe
 */
public class HttpUtil {
private static Logger logger = Logger.getLogger(HttpUtil.class);
	
	/**
	 * 发送Http请求
	 * @param urlStr url参数
	 * @param params http正文
	 * @return http响应串
	 */
	public static String sendRequest(String urlStr, String params) {
		String  resultStr = "";
        int retryNum = 0;
        while(retryNum <= 2){
             retryNum++;
             try {
                  URL url = new URL(urlStr);
                  HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                  conn.setDoInput(true);
                  conn.setDoOutput(true);
                  conn.setUseCaches(false);
	              conn.setConnectTimeout(6 * 1000); 
	              conn.setReadTimeout(6*1000); 
                //conn.setRequestMethod("GET");
                  conn.setRequestMethod("POST");
                  conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:31.0) Gecko/20100101 Firefox/31.0"); 
                  conn.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01"); 
                  conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
                  conn.setRequestProperty("Content-Language","zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3" );
                  conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
                  OutputStreamWriter out = new OutputStreamWriter (conn.getOutputStream(), "UTF-8");
                  out.write(params);//POST方式
                  out.flush();
                  out.close();
                  
                  //判断response 是否为gzip格式
                  String responseEncoding = conn.getHeaderField("Content-Encoding");
                  boolean isGzipResponse = "gzip".equalsIgnoreCase(responseEncoding);
                  
                  BufferedReader reader = null;
                  if(isGzipResponse) {
                	  reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(conn.getInputStream()), "utf-8" ));
                  } else {
                	  reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8" ));
                  }


                  String currentLine = "";
                  while ((currentLine = reader.readLine()) != null) {
                       resultStr += currentLine;
                  }
                  reader.close();
                  break;
             }catch(ConnectException e){//网络故障导致的通讯失败
                  logger.info("第"+retryNum+"次请求失败！");
                  if( retryNum == 3){
                       resultStr = "";
                       logger.info("通讯失败：请检查网络和服务器是否正常！");
                       //throw new RuntimeException("通讯失败：请检查网络和服务器是否正常！");
                  }
             } catch (IOException e) {
                  logger.info("第"+retryNum+"次请求" + urlStr + "异常！");
             }
        }
        return resultStr;
	}
	
}
