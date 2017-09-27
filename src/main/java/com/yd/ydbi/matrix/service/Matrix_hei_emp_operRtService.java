package com.yd.ydbi.matrix.service;

import com.yd.ydbi.matrix.model.Matrix_hei_emp_oper_rt;
import java.util.List;
import java.util.Map;

public interface Matrix_hei_emp_operRtService {
	List<Matrix_hei_emp_oper_rt> searchData(Map<String, Object> arg0);

	List<Matrix_hei_emp_oper_rt> searchFooterData(Map<String, Object> arg0);
}
