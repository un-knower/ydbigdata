package com.yd.ydbi.cainiao.service.impl;

import com.yd.ydbi.cainiao.dao.CaiNiaoMapper;
import com.yd.ydbi.cainiao.model.CaiNiao;
import com.yd.ydbi.cainiao.service.CaiNiaoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaiNiaoServiceImpl
  implements CaiNiaoService
{

  @Autowired
  CaiNiaoMapper dataDao;

  public List<CaiNiao> searchData(Map<String, Object> paramsMap)
  {
    List cnlist = this.dataDao.searchData(paramsMap);
    return cnlist;
  }

  public List<CaiNiao> searchFooterData(Map<String, Object> paramsMap) {
    List cnd = this.dataDao.searchFooterData(paramsMap);
    return cnd;
  }

  public CaiNiao getCaiNiaoBystatsDt(Map<String, Object> paramsMap) {
    CaiNiao cn = this.dataDao.getCaiNiaoBystatsDt(paramsMap);
    return cn;
  }

  public void insertCn(Map<String, Object> paramsMap) {
    this.dataDao.insertCn(paramsMap);
  }

  public int updateData(Map<String, Object> paramsMap) {
    int ucn = this.dataDao.updateData(paramsMap);
    return ucn;
  }

  public int deleteData(Map<String, Object> paramsMap) {
    List list = new ArrayList();
    list = (List)paramsMap.get("strIds");
    int dcn = this.dataDao.deleteData(list);
    return dcn;
  }
}