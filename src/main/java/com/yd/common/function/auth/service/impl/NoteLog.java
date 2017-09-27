package com.yd.common.function.auth.service.impl;

import com.yd.common.function.admin.dao.CIP_admin_op_logDao;
import com.yd.common.function.admin.data.po.CIP_admin_op_logPO;

/**
 * ClassName:NoteLog
 * Function: TODO ADD FUNCTION.
 * Reason: 多线程并发记录登录日志.
 * Date: 2016年5月11日 上午8:31:12
 * @author liuxianglong
 * @version
 * @since JDK 1.7
 * @see
 */
public class NoteLog implements Runnable {

	private CIP_admin_op_logDao op_logDao;
	private CIP_admin_op_logPO op_logPO;
	
	public NoteLog(CIP_admin_op_logDao op_logDao,CIP_admin_op_logPO op_logPO) {
		this.op_logDao = op_logDao;
		this.op_logPO = op_logPO;
	}
	@Override
	public void run() {
		op_logDao.add(op_logPO);
	}

}
