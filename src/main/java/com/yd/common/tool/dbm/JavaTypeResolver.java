package com.yd.common.tool.dbm;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class JavaTypeResolver {

    private static boolean forceBigDecimals;

    private static Map<Integer, String> typeMap;
    private static Map<Integer, String> fullTypeMap;

    static {
        typeMap = new HashMap<Integer, String>();

        typeMap.put(Types.ARRAY, "Object");
        typeMap.put(Types.BIGINT, "long");
        typeMap.put(Types.BINARY, "byte[]");
        typeMap.put(Types.BIT, "boolean");
        typeMap.put(Types.BLOB, "byte[]");
        typeMap.put(Types.BOOLEAN, "boolean");
        typeMap.put(Types.CHAR, "String");
        typeMap.put(Types.CLOB, "String");
        typeMap.put(Types.DATALINK, "String");
        typeMap.put(Types.DATE, "Date");
        typeMap.put(Types.DISTINCT, "Object");
        typeMap.put(Types.DOUBLE, "double");
        typeMap.put(Types.FLOAT, "float");
        typeMap.put(Types.INTEGER, "int");
        typeMap.put(Types.JAVA_OBJECT, "Object");
        typeMap.put(Types.LONGNVARCHAR, "String");
        typeMap.put(Types.LONGVARBINARY, "byte[]");
        typeMap.put(Types.LONGVARCHAR, "String");
        typeMap.put(Types.NCHAR, "String");
        typeMap.put(Types.NCLOB, "String");
        typeMap.put(Types.NVARCHAR, "String");
        typeMap.put(Types.NULL, "Object");
        typeMap.put(Types.OTHER, "Object");
        typeMap.put(Types.REAL, "double");
        typeMap.put(Types.REF, "Object");
        typeMap.put(Types.SMALLINT, "int");
        typeMap.put(Types.STRUCT, "Object");
        typeMap.put(Types.TIME, "Time");
        typeMap.put(Types.TIMESTAMP, "Timestamp");
        typeMap.put(Types.TINYINT, "int");
        typeMap.put(Types.VARBINARY, "byte[]");
        typeMap.put(Types.VARCHAR, "String");


        fullTypeMap = new HashMap<Integer, String>();

        fullTypeMap.put(Types.ARRAY, "java.lang.Object");
        fullTypeMap.put(Types.BIGINT, "java.lang.Long");
        fullTypeMap.put(Types.BINARY, "byte[]");
        fullTypeMap.put(Types.BIT, "java.lang.Boolean");
        fullTypeMap.put(Types.BLOB, "byte[]");
        fullTypeMap.put(Types.BOOLEAN, "java.lang.Boolean");
        fullTypeMap.put(Types.CHAR, "java.lang.String");
        fullTypeMap.put(Types.CLOB, "java.lang.String");
        fullTypeMap.put(Types.DATALINK, "java.lang.String");
        fullTypeMap.put(Types.DATE, "java.sql.Date");
        fullTypeMap.put(Types.DISTINCT, "java.lang.Object");
        fullTypeMap.put(Types.DOUBLE, "java.lang.Double");
        fullTypeMap.put(Types.FLOAT, "java.lang.Double");
        fullTypeMap.put(Types.INTEGER, "java.lang.Integer");
        fullTypeMap.put(Types.JAVA_OBJECT, "java.lang.Object");
        fullTypeMap.put(Types.LONGNVARCHAR, "java.lang.String");
        fullTypeMap.put(Types.LONGVARBINARY, "byte[]");
        fullTypeMap.put(Types.LONGVARCHAR, "java.lang.String");
        fullTypeMap.put(Types.NCHAR, "java.lang.String");
        fullTypeMap.put(Types.NCLOB, "java.lang.String");
        fullTypeMap.put(Types.NVARCHAR, "java.lang.String");
        fullTypeMap.put(Types.NULL, "java.lang.Object");
        fullTypeMap.put(Types.OTHER, "java.lang.Object");
        fullTypeMap.put(Types.REAL, "java.lang.Float");
        fullTypeMap.put(Types.REF, "java.lang.Object");
        fullTypeMap.put(Types.SMALLINT, "java.lang.Short");
        fullTypeMap.put(Types.STRUCT, "java.lang.Object");
        fullTypeMap.put(Types.TIME, "java.sql.Time");
        fullTypeMap.put(Types.TIMESTAMP, "java.sql.Timestamp");
        fullTypeMap.put(Types.TINYINT, "java.lang.Byte");
        fullTypeMap.put(Types.VARBINARY, "byte[]");
        fullTypeMap.put(Types.VARCHAR, "java.lang.String");
    }

    public static String getJavaType(CIPColumn column) {
        String answer;
        String javaType = typeMap.get(column.getJdbcType());

        if (javaType == null) {
            switch (column.getJdbcType()) {
                case Types.DECIMAL:
                case Types.NUMERIC:
                    if (column.getDecimalDigits() > 0 || column.getSize() > 18 || forceBigDecimals) {
                        answer = "BigDecimal";
                    } else if (column.getSize() > 9) {
                        answer = "long";
                    } else if (column.getSize() > 4) {
                        answer = "int";
                    } else {
                        answer = "short";
                    }
                    break;

                default:
                    answer = null;
                    break;
            }
        } else {
            answer = javaType;
        }

        return answer;
    }

    public static String getFullJavaType(CIPColumn column) {
        String answer;
        String javaType = fullTypeMap.get(column.getJdbcType());

        if (javaType == null) {
            switch (column.getJdbcType()) {
                case Types.DECIMAL:
                case Types.NUMERIC:
                    if (column.getDecimalDigits() > 0 || column.getSize() > 18 || forceBigDecimals) {
                        answer = "java.math.BigDecimal";
                    } else if (column.getSize() > 9) {
                        answer = "java.lang.Long";
                    } else if (column.getSize() > 4) {
                        answer = "java.lang.Integer";
                    } else {
                        answer = "java.lang.Short";
                    }
                    break;
                case Types.TIMESTAMP:
                	answer = "java.sql.Timestamp";
                	break;
                default:
                    answer = null;
                    break;
            }
        } else {
            answer = javaType;
        }

        return answer;
    }
    
    public static boolean isFloat(String javaType) {
        if (javaType.endsWith("Float") || javaType.endsWith("Double") || javaType.endsWith("BigInteger")) {
            return true;
        }
        if (javaType.endsWith("float") || javaType.endsWith("double") || javaType.endsWith("BigInteger")) {
            return true;
        }
        return false;
    }

    public static boolean isBigDecimal(String javaType) {
        return javaType.endsWith("BigDecimal");
    }

    public static boolean isInteger(String javaType) {
        if (javaType.endsWith("Long") || javaType.endsWith("Integer") || javaType.endsWith("Short")
            || javaType.endsWith("Byte")) {
            return true;
        }
        if (javaType.endsWith("long") || javaType.endsWith("int") || javaType.endsWith("short")
            || javaType.endsWith("byte")) {
            return true;
        }
        return false;
    }

    public static boolean isDate(String javaType) {
        if (javaType.endsWith("Date") || javaType.endsWith("Timestamp") || javaType.endsWith("Time")) {
            return true;
        }
        return false;
    }

    public static boolean isString(String javaType) {
        if (javaType.endsWith("String")) {
            return true;
        }
        return false;
    }

    public static boolean isBoolean(String javaType) {
        if (javaType.endsWith("Boolean") || javaType.endsWith("boolean")) {
            return true;
        }
        return false;
    }
}