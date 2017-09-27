package com.yd.common.session;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

/**
 * ClassName:CIPStaticResourcesFilter
 * Function: TODO ADD FUNCTION.
 * Reason: TODO ADD REASON.
 * Date: 2016年5月6日 下午3:59:18
 * @author liupengfei
 * @version
 * @since JDK 1.7
 * @see
 * 过滤请求，加载公用静态资源
 */
public class CIPStaticResourcesFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		
		httpResponse.setContentType("text/html");
		
		InputStream input = null;
		
		input = LoadStaticResources.getResource(httpRequest.getRequestURI());
		
		
		if(input != null){
			try{
				String extension = getFileExtension(httpRequest.getRequestURI());
				httpResponse.setContentType(getContentType(extension));
				
				IOUtils.copy(input, httpResponse.getOutputStream());
			}finally{
				IOUtils.closeQuietly(input);
			}
		}else{
			
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void destroy() {

	}
	
	
	private static String getFileExtension(String filepath){
		
		int intex = filepath.lastIndexOf(".");
		String extension = filepath.substring(intex);
		
		return extension;
	}
	
	private static String getContentType(String file_extension){
		
		Map<String, String> contentTypes = new HashMap<String, String>();
		
		contentTypes.put(".js", "application/x-javascript");
		contentTypes.put(".css", "text/css");
		contentTypes.put(".png", "image/png");
		contentTypes.put(".gif", "image/gif");
		contentTypes.put(".jpg", "image/jpeg");
		contentTypes.put(".ico", "image/x-icon");
		contentTypes.put(".html", "text/html");
		
		return contentTypes.get(file_extension);
	}
	
	
}
