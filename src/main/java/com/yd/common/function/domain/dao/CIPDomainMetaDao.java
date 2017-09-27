package com.yd.common.function.domain.dao;

import org.springframework.stereotype.Repository;

import com.yd.common.tool.dbm.CIPTable;

@Repository
public interface CIPDomainMetaDao {

	public CIPTable getDomainTable(String tableName);
	
}
