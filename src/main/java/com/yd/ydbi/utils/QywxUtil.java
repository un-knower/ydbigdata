package com.yd.ydbi.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.yd.common.conf.CIPConfigureManager;
import com.yd.ydbi.common.response.CustomResponseMsg;

public class QywxUtil {
	private static final Logger logger = LoggerFactory.getLogger(QywxUtil.class);
	
    public static String getWXuserId(String code) {
    	String userid = code;
        try {
        	String corpsecret = CIPConfigureManager.getRootConf().getProperty("qywx.corpsecret").trim();
        	String url = CIPConfigureManager.getRootConf().getProperty("qywx.url").trim();
			ObjectMapper objectMapper =  new ObjectMapper();
			Map<String, String> map = new HashMap<String, String>();
			map.put("code", code);
			map.put("corpsecret",corpsecret);
			String jsonfromMap =  objectMapper.writeValueAsString(map);
			logger.info(jsonfromMap);
			String s = sendPost(url, jsonfromMap);
			logger.info(s);
			CustomResponseMsg json = JSON.parseObject(s, CustomResponseMsg.class);
			Map<String,String> result_map = (Map<String,String>)json.getData();
			userid = result_map.get("userid"); 
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return userid;
    }

    public static String sendPost(String url,String Params)throws IOException {
        OutputStreamWriter out = null;
        BufferedReader reader = null;
        String response = "";
        try {
            URL httpUrl = null; //HTTP URL类 用这个类来创建连接
            //创建URL
            httpUrl = new URL(url);
            //建立连接
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("connection", "keep-alive");
            conn.setUseCaches(false);//设置不要缓存
            conn.setInstanceFollowRedirects(true);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            //POST请求
            out = new OutputStreamWriter(conn.getOutputStream());
            out.write(Params);
            out.flush();
            //读取响应
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String lines;
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                response += lines;
            }
            reader.close();
            // 断开连接
            conn.disconnect();

        } catch (Exception e) {
        	logger.info("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return response;
    }

}
