package ${basePackage}.${structure.str_module}.dao;

import org.springframework.stereotype.Repository;

import ${basePackage}.${structure.str_module}.data.po.${className}PO;
import ${basePackage}.${structure.str_module}.data.${className}Data;

import java.util.List;
import java.util.Map;

import com.yd.common.dao.IDao;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;

/**
 * <p>Dao类</p>
 * <p>Class: ${className} - ${structure.str_name}</p>
 *
 * @since ${.now}
 */
 
@Repository
public interface ${className}Dao extends IDao<${className}PO> {
	
	public List<${className}Data> searchData(CIPPageInfo page, CIPReqCondition[] conditions);
	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page, CIPReqCondition[] conditions, boolean xFirst);
}
