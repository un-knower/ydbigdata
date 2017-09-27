package com.yd.ydbi.business.dao;

import com.yd.ydbi.business.model.BasSBusiNodelv7Tmp0;

public interface BasSBusiNodelv7Tmp0Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BasSBusiNodelv7Tmp0 record);

    int insertSelective(BasSBusiNodelv7Tmp0 record);

    BasSBusiNodelv7Tmp0 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BasSBusiNodelv7Tmp0 record);

    int updateByPrimaryKey(BasSBusiNodelv7Tmp0 record);
}