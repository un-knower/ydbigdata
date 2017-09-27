package com.yd.common.session;

import java.io.InputStream;

/**
 * ClassName:LoadStaticResources
 * Function: TODO ADD FUNCTION.
 * Reason: TODO ADD REASON.
 * Date: 2016年5月6日 下午4:03:33
 * @author liupengfei
 * @version
 * @since JDK 1.7
 * @see
 * 返回文件对应的流
 */
class LoadStaticResources {

	static InputStream getResource(String path){
		
		int start = path.indexOf("/", 1);
		
		String filePath = path.substring(start);
		
		return LoadStaticResources.class.getResourceAsStream("/staticresources"+filePath);
		
	}
	
}
