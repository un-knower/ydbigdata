package com.yd.common.tool.dbm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CIPDefaultDatabase extends CIPDatabase {

	public CIPDefaultDatabase(Connection connection) {
		super(connection);
	}

	protected void introspectPrimaryKeys(CIPTable table) throws SQLException {
		ResultSet rs = null;
		try {
			rs = connection.getMetaData().getPrimaryKeys(null,
					table.getSchema(), table.getTableName());
			while (rs.next()) {
				String columnName = rs.getString("COLUMN_NAME");
				CIPColumn column = table.getColumn(columnName);
				if (column == null) {
					column = new CIPColumn(columnName);
					table.addPrimaryKey(column);
				}
				column.setPrimaryKey(true);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			close(rs);
		}
	}

	protected void introspectColumns(CIPTable table) throws SQLException {
		ResultSet rs = null;
		try {
			rs = connection.getMetaData().getColumns(null, table.getSchema(),
					table.getTableName(), "%");
			while (rs.next()) {
				String columnName = rs.getString("COLUMN_NAME");// 获得字段名称
				if (StringUtil.isEmpty(columnName)) {
					continue;
				}

				CIPColumn column = table.getColumn(columnName);
				if (column == null) {
					column = new CIPColumn(columnName);
					table.addColumn(column);
				}
				column.setJavaProperty(columnName);
				column.setJdbcType(rs.getInt("DATA_TYPE"));
				column.setSize(rs.getInt("COLUMN_SIZE"));
				column.setNullable(rs.getInt("NULLABLE") == 1);
				column.setDefaultValue(rs.getString("COLUMN_DEF"));
				column.setDecimalDigits(rs.getInt("DECIMAL_DIGITS"));
				column.setRemarks(rs.getString("REMARKS"));
				column.setJdbcTypeName(JdbcTypeResolver.getJdbcTypeName(column
						.getJdbcType()));
				column.setJavaType(JavaTypeResolver.getJavaType(column));
				column.setFullJavaType(JavaTypeResolver.getFullJavaType(column));

				// 根据字段定义规则，生成ui相关类型
				column.generateUIInfo();

				// 添加mvc引用java类信息
				table.addRefJavaClass(column.getFullJavaType());

			}
		} catch (SQLException e) {
			throw e;
		} finally {
			close(rs);
		}
	}

	// 获得索引
	protected void introspectIndex(CIPTable table) throws SQLException {
		ResultSet rs = null;
		try {
			rs = connection.getMetaData().getIndexInfo(null, table.getSchema(),
					table.getTableName(), true, true);
			while (rs.next()) {
				String columnName = rs.getString("COLUMN_NAME");
				if (StringUtil.isEmpty(columnName)) {
					continue;
				}
				CIPColumn column = table.getColumn(columnName);
				if (column != null) {
					column.setUnique(!rs.getBoolean("NON_UNIQUE"));
				}
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			close(rs);
		}
	}

	private CIPTable retrieveInfo(ResultSet rs) throws SQLException {
		CIPTable table = new CIPTable();
		table.setCatalog(rs.getString("TABLE_CAT"));
		table.setSchema(rs.getString("TABLE_SCHEM"));
		table.setTableName(rs.getString("TABLE_NAME"));
		table.setRemarks(rs.getString("REMARKS"));
		table.setTableType(rs.getString("TABLE_TYPE"));

		// 获取主键信息
		introspectPrimaryKeys(table);

		// 获取所有字段信息
		introspectColumns(table);

		// 获取索引信息
		introspectIndex(table);

		// 生成关联码表信息
		generateDomainTables(table);
		
		//生成视图信息
		generateViewInfo(table);

		return table;
	}

	public void generateViewInfo(CIPTable table) {
	}

	private void generateDomainTables(CIPTable table) throws SQLException {
		List<CIPColumn> columns = table.getColumns();
		List<CIPDomainInfo> domainTables = new ArrayList<CIPDomainInfo>();
		CIPDomainInfo domain;
		// 1、获取要关联的码表信息
		for (CIPColumn column : columns) {
			if (column.getPropertyType() == 1) {
				// 关联码表信息，获取文本
				domain = getCIPDomain(column.getJavaProperty());

				if (domain != null) {
					domain.domainId = column.getJavaProperty();
					if (domain.refDomainId != null
							&& !domain.refDomainId.equals("")) {
						domain = getCIPDomain(column.getJavaProperty());
					}
					if (domain == null)
						continue;
					domain.domainId = column.getJavaProperty();
					
					table.addTxtColumn(domain.domainId, column.getRemarks());

					domainTables.add(domain);
				}
			}
		}
		
		table.setDomainTables(domainTables);
	}

	private CIPDomainInfo getCIPDomain(String domainId)	throws SQLException {
		CIPDomainInfo domain = null;
		String sql = "select * from cip_admin_domain where domain_id='"
				+ domainId + "'";
		PreparedStatement ptst = connection.prepareStatement(sql);
		ResultSet rs = ptst.executeQuery();
		if (rs.next()) {
			domain = new CIPDomainInfo();
			domain.refDomainId = rs.getString("ref_domain_id");
			domain.refTableId = rs.getString("ref_table_id");
		}

		return domain;
	}

	@Override
	public List<CIPTable> getTables(String catalog, String schema) {
		ResultSet rs = null;
		CIPTable table = null;
		List<CIPTable> tables = null;
		try {
			// 获取所有表
			rs = connection.getMetaData().getTables(catalog, schema, "%",
					new String[] { "TABLE" });
			tables = new ArrayList<CIPTable>(rs.getFetchSize());
			while (rs.next()) {
				table = retrieveInfo(rs);
				tables.add(table);
			}
		} catch (SQLException e) {
			return null;
		} finally {
			close(rs);
		}
		return tables;
	}

	@Override
	public CIPTable getTable(String catalog, String schema, String tableName)
			throws SQLException {
		ResultSet rs = null;
		CIPTable table = null;
		try {
			rs = connection.getMetaData().getTables(catalog, schema, tableName,
					new String[] { "TABLE" });
			if (rs.next()) {
				table = retrieveInfo(rs);
			}

		} catch (SQLException e) {
			throw e;
		} finally {
			close(rs);
		}
		return table;
	}

	@Override
	public List<CIPTable> getViews(String catalog, String schema) {
		ResultSet rs = null;
		CIPTable table = null;
		List<CIPTable> tables = null;
		try {
			rs = connection.getMetaData().getTables(catalog, schema, "%",
					new String[] { "VIEW" });
			tables = new ArrayList<CIPTable>(rs.getFetchSize());
			while (rs.next()) {
				table = retrieveInfo(rs);

				tables.add(table);
			}
		} catch (SQLException e) {
			return null;
		} finally {
			close(rs);
		}
		return tables;
	}

	@Override
	public CIPTable getView(String catalog, String schema, String viewName)
			throws SQLException {
		ResultSet rs = null;
		CIPTable table = null;
		try {
			rs = connection.getMetaData().getTables(catalog, schema, viewName,
					new String[] { "VIEW" });
			if (rs.next()) {
				table = retrieveInfo(rs);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			close(rs);
		}
		return table;
	}
}
