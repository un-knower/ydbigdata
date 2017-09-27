package com.yd.ydbi.cainiao.service;

import com.yd.ydbi.cainiao.model.CaiNiaoDxl;
import com.yd.ydbi.cainiao.model.CaiNiaoWeek;
import com.yd.ydbi.common.function.model.ComboxBean;

import java.util.List;
import java.util.Map;

public abstract interface CaiNiaoDtlService
{
  public abstract List<CaiNiaoDxl> searchDxlData(Map<String, Object> paramMap);

  public abstract List<CaiNiaoDxl> searchFooterData(Map<String, Object> paramMap);

  public abstract List<CaiNiaoWeek> searchWeekData(Map<String, Object> paramMap);

  public abstract List<CaiNiaoWeek> searchWeekFooterData(Map<String, Object> paramMap);

  public abstract void importData(Map<String, List<CaiNiaoDxl>> paramMap);

  public abstract int deleteData(Map<String, Object> paramMap);
  
  public List<ComboxBean> getLineRegion(Map<String, String> paramsMap);
}