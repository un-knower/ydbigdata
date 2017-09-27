package com.yd.ydbi.business.dao;

import com.yd.ydbi.business.model.BasSBusiDelv3Tmp0;

public interface BasSBusiDelv3Tmp0Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BasSBusiDelv3Tmp0 record);

    int insertSelective(BasSBusiDelv3Tmp0 record);

    BasSBusiDelv3Tmp0 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BasSBusiDelv3Tmp0 record);

    int updateByPrimaryKey(BasSBusiDelv3Tmp0 record);
}