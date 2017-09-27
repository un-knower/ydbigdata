package com.yd.ydbi.business.dao;

import com.yd.ydbi.business.model.BasSBusiSndTmp0;

public interface BasSBusiSndTmp0Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BasSBusiSndTmp0 record);

    int insertSelective(BasSBusiSndTmp0 record);

    BasSBusiSndTmp0 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BasSBusiSndTmp0 record);

    int updateByPrimaryKey(BasSBusiSndTmp0 record);
}