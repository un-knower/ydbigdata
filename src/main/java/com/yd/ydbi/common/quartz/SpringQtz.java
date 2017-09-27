package com.yd.ydbi.common.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;


/**
 * @author kangyuanjia
 * @createTime 2017-03-14 09:29:34
 * @describe 作业类的调度
 */
public class SpringQtz extends QuartzJobBean {

	static Logger logger = LoggerFactory.getLogger(SpringQtz.class);

	private static int counter = 0;

	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		long ms = System.currentTimeMillis();
		logger.error(" SpringQtz start  执行");
		logger.info("-------" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(ms)) + "  " + "("
				+ counter++ + ")");
	}

}