package com.yd.common.tool.dbm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class CIPDatabase {

    protected Connection connection;

    public CIPDatabase(Connection connection){
        this.connection = connection;
    }

    public abstract List<CIPTable> getTables(String catalog, String schema);
    
    public abstract CIPTable getTable(String catalog, String schema, String tableName) throws SQLException;

    public abstract List<CIPTable> getViews(String catalog, String schema);
    
    public abstract CIPTable getView(String catalog, String schema, String viewName) throws SQLException;
    
    public abstract void generateViewInfo(CIPTable table);
    
    public Connection getConnection() {
        return connection;
    }

    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
        } catch (SQLException e) {

        }
    }

    public static void close(Statement st) {
        try {
            if (st != null) {
                st.close();
                st = null;
            }
        } catch (SQLException e) {

        }
    }
    
    public static CIPDatabase createDatabase(Connection connection) throws SQLException {
        
    	String dbProduct = connection.getMetaData().getDatabaseProductName();
        if(dbProduct.toLowerCase().contains("oracle")){
            return new OracleDatabase(connection);
        } else if(dbProduct.toLowerCase().contains("sql server")){
            return new SqlServerDatabase(connection);
        } else if(dbProduct.toLowerCase().contains("mysql")){
            return new MySqlDatabase(connection);
        } else {
            return new CIPDefaultDatabase(connection);
        }
    }
}
