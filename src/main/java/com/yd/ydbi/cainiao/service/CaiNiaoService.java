package com.yd.ydbi.cainiao.service;

import com.yd.ydbi.cainiao.model.CaiNiao;
import java.util.List;
import java.util.Map;

public abstract interface CaiNiaoService
{
  public abstract List<CaiNiao> searchData(Map<String, Object> paramMap);

  public abstract List<CaiNiao> searchFooterData(Map<String, Object> paramMap);

  public abstract CaiNiao getCaiNiaoBystatsDt(Map<String, Object> paramMap);

  public abstract void insertCn(Map<String, Object> paramMap);

  public abstract int updateData(Map<String, Object> paramMap);

  public abstract int deleteData(Map<String, Object> paramMap);
}