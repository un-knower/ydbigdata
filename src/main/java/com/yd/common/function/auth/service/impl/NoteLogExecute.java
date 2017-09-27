package com.yd.common.function.auth.service.impl;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ClassName:NoteLogExecute
 * Function: TODO ADD FUNCTION.
 * Reason: 用线程池处理并发解决登录日志影响登录性能的问题.
 * Date: 2016年5月11日 上午8:34:15
 * @author liupengfei
 * @version
 * @since JDK 1.7
 * @see
 */
public class NoteLogExecute {

	
	private  static ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 3, 200, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()) ;
	
	private NoteLogExecute(){
		
	}
	
	public static void execute(Runnable job){
		executor.execute(job);
	}
	
}
