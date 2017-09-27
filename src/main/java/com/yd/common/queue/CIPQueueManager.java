package com.yd.common.queue;

import java.util.List;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.yd.common.function.admin.service.CIP_admin_queueService;
import com.yd.common.runtime.CIPRuntimeOperator;

public class CIPQueueManager {
	private static CIPQueueManager queueManager;
	private CIP_admin_queueService queueService;
	
	public static CIPQueueManager getQueueManager(){
		if(queueManager == null){
			queueManager = new CIPQueueManager();
			WebApplicationContext wc = ContextLoader.getCurrentWebApplicationContext();  
			queueManager.queueService = (CIP_admin_queueService) wc.getBean("cipQueueService");
		}
		
		return queueManager;
	}
	
	//1.获取队列数据
	public Object popQueue(String queueType){
		return queueService.popQueue(queueType);
	}
	
	public List<Object> popQueue(String queueType, int count){
		return queueService.popQueue(queueType, count);
	}
	
	//2.写队列数据
	public void pushQueue(String queueType, Object queueEntry, CIPRuntimeOperator operateInf){
		queueService.pushQueue(queueType, queueEntry, operateInf);
	}
	
	//3.删除队列数据
	public void removePopEntries(String queueType, CIPRuntimeOperator operateInf){
		queueService.removePopEntries(queueType, operateInf);
	}
	
	public void clearQueue(String queueType, CIPRuntimeOperator operateInf){
		queueService.clearQueue(queueType, operateInf);
	}
	
	
}
