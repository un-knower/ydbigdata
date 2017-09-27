package com.yd.common.text;

import java.util.List;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.yd.common.function.admin.service.CIP_admin_textService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntimeConfigure;

public class CIPTextManager {

	private static CIPTextManager textManager;
	private CIP_admin_textService textService;

	public static CIPTextManager gettextManager() {
		if (textManager == null) {
			textManager = new CIPTextManager();
			WebApplicationContext wc = ContextLoader
					.getCurrentWebApplicationContext();
			textManager.textService = (CIP_admin_textService) wc
					.getBean("cipTextService");
		}

		return textManager;
	}
	
	public static CIPErrorCode getErrorCode(String lang, int code, String name) {
		if( lang.equals(CIPRuntimeConfigure.cip_default_lang))
			return new CIPErrorCode( code, name );
		
		gettextManager();
		
		String text = textManager.getMessageText(lang, code);
		
		return new CIPErrorCode(code,text);
	}
	
	public static CIPErrorCode getErrorCode(String lang, int code, List<String> params) {
		gettextManager();
		String text = textManager.getMessageText(lang, code, params);
		return new CIPErrorCode(code,text);
	}

	public String getMessageText(String lang, int textNo) {

		Object[] keys = new Object[] { lang, textNo };

		return textService.getText(keys);
	}

	public String getMessageText(String lang, int textNo,
			List<String> params) {
		Object[] keys = new Object[] {lang,textNo};
		String text = textService.getText(keys);
		if(params == null || text == null)
			return text;
		int size = params.size();
		String param;
		for(int i=0;i<size;i++){
			param = params.get(i);
			text = text.replaceAll("&"+i, param);
		}
		return text;
	}

}
