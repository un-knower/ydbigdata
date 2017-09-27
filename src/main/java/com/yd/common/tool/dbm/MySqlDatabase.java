package com.yd.common.tool.dbm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MySqlDatabase extends CIPDefaultDatabase {

    private static final String TABLE_COMMENTS_SQL  = "show table status where NAME=?";

    public MySqlDatabase(Connection connection){
        super(connection);
    }

    @Override
    public void generateViewInfo(CIPTable table) {
    	String viewContent = null;
		StringBuffer outColumns = new StringBuffer();
		StringBuffer selectBody = new StringBuffer();
		List<CIPDomainInfo> domainTables = table.getDomainTables();
		if(domainTables .size()>0){
			outColumns.append("DROP VIEW IF EXISTS ").append(table.getTableName()).append("_v;\n");
			outColumns.append("create ALGORITHM=MERGE view ").append(table.getTableName()).append("_v as \n").append("select m.*\n");
			
			selectBody.append(" from ").append(table.getTableName()).append(" m ");
			for(CIPDomainInfo d:domainTables){
				
				if(!d.refTableId.equals("cip_admin_codes")){
					outColumns.append(",").append(d.domainId).append(".").append(d.domainId).append("_name ");
					selectBody.append("\n left join ").append(d.refTableId).append(" ").append(d.domainId);
					selectBody.append("\n on m.").append(d.domainId).append("=").append(d.domainId).append(".").append(d.domainId);
				}
				else {
					outColumns.append(",").append(d.domainId).append(".code_name as ").append(d.domainId).append("_name ");
					selectBody.append("\n left join ").append(d.refTableId).append(" ").append(d.domainId);
					selectBody.append("\n on m.").append(d.domainId).append("=").append(d.domainId).append(".code_type \n and ");
					selectBody.append(d.domainId).append(".domain_id='").append(d.domainId).append("' ");
				}
			}
		}
		else {
			outColumns.append("DROP VIEW IF EXISTS ").append(table.getTableName()).append("_v;\n");
			outColumns.append("create ALGORITHM=MERGE view ").append(table.getTableName()).append("_v as ").append("select m.*");
			
			selectBody.append(" from ").append(table.getTableName()).append(" m ");
		}
		
		outColumns.append(selectBody).append(";\n");
		viewContent = outColumns.toString();
		
		table.setViewSql(viewContent);
	}

	@Override
	public List<CIPTable> getTables(String catalog, String schema) {
		List<CIPTable> tables = super.getTables(catalog, schema);
		for( CIPTable table : tables) {
			try {
				introspectTableComments(table);
			} catch (SQLException e) {
				;
			}
		}
		
		return tables;
	}
    
    @Override
    public CIPTable getTable(String catalog, String schema, String tableName) throws SQLException {
        CIPTable table = super.getTable(catalog, schema, tableName);
        if (table != null) {
            introspectTableComments(table);
        }
        return table;
    }
    
    @Override
	public List<CIPTable> getViews(String catalog, String schema) {
		List<CIPTable> tables = super.getViews(catalog, schema);
		for( CIPTable table : tables) {
			try {
				introspectTableComments(table);
			} catch (SQLException e) {
				;
			}
		}
		
		return tables;
	}
    
    @Override
    public CIPTable getView(String catalog, String schema, String tableName) throws SQLException {
        CIPTable table = super.getView(catalog, schema, tableName);
        if (table != null) {
            introspectTableComments(table);
        }
        return table;
    }


    public void introspectTableComments(CIPTable table) throws SQLException {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            psmt = connection.prepareStatement(TABLE_COMMENTS_SQL);
            psmt.setString(1, table.getTableName());
            rs = psmt.executeQuery();
            if (rs.next()) {
                table.setRemarks(rs.getString("COMMENT"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            close(rs);
            close(psmt);
        }
    }
}
