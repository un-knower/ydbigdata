package com.yd.ydbi.cainiao.service.impl;

import com.yd.ydbi.cainiao.dao.CaiNiaoDxlMapper;
import com.yd.ydbi.cainiao.model.CaiNiaoDxl;
import com.yd.ydbi.cainiao.model.CaiNiaoWeek;
import com.yd.ydbi.cainiao.service.CaiNiaoDtlService;
import com.yd.ydbi.common.function.model.ComboxBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaiNiaoDtlServiceImpl
  implements CaiNiaoDtlService
{

  @Autowired
  CaiNiaoDxlMapper dataDao;

  public List<CaiNiaoDxl> searchDxlData(Map<String, Object> paramsMap)
  {
    List<CaiNiaoDxl> cnd = this.dataDao.searchDxlData(paramsMap);
    return cnd;
  }

  public List<CaiNiaoDxl> searchFooterData(Map<String, Object> paramsMap) {
    List<CaiNiaoDxl> cnd = this.dataDao.searchFooterData(paramsMap);
    return cnd;
  }

  public List<CaiNiaoWeek> searchWeekData(Map<String, Object> paramsMap)
  {
    List<CaiNiaoWeek> cnd = this.dataDao.searchWeekData(paramsMap);
    List<CaiNiaoWeek> cndAll = this.dataDao.searchSumWeekData(paramsMap);
    if (cnd.size() > 0 && cndAll.size()>0)
    {
      cnd.add(0, (CaiNiaoWeek)cndAll.get(0));
    }

    return cnd;
  }

  public List<CaiNiaoWeek> searchWeekFooterData(Map<String, Object> paramsMap) {
    List<CaiNiaoWeek> cnd = this.dataDao.searchWeekFooterData(paramsMap);
    return cnd;
  }

  public void importData(Map<String, List<CaiNiaoDxl>> dataListMap)
  {
    List<CaiNiaoDxl> pos = new ArrayList<CaiNiaoDxl>();
    pos = (List)dataListMap.get("dataList");
    this.dataDao.insertData(pos);
  }

  public int deleteData(Map<String, Object> paramsMap)
  {
    List<String> list = new ArrayList<String>();
    list = (List)paramsMap.get("strIds");
    int dcn = this.dataDao.deleteData(list);
    return dcn;
  }

	@Override
	public List<ComboxBean> getLineRegion(Map<String, String> paramsMap) {
		List<ComboxBean> pos = new ArrayList<ComboxBean>();
		pos = dataDao.getLineRegion(paramsMap);
		return pos;
	}
}