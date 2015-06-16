package com.sinoair.iemisgateway.util;

//

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;


//

/**
 * <p>ClassName: ExeSQL </p>
 * <p>Description: 查询类 </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: sinosoft </p>
 *
 * @CreateDate：2007-06-28
 */
public class ExeSQL {
    public static final String PACKAGESPILTER = "|";
    public static final String RECORDSPLITER = "^";
    public static final String ENDOFPARAMETER = "^";
    private Connection m_connection;
    private boolean mflag = false;

    // @Constructor
    public ExeSQL(Connection tConnection) {
        m_connection = tConnection;
        mflag = true;
    }

    public ExeSQL() {
    }

    public void setConnection(Connection tConnection) {
        m_connection = tConnection;
        mflag = true;
    }


    public HashMap execSqltoHash(String sql, int maxRowCount) {
        System.out.println("ExecSQL : " + sql);

        HashMap rowset = new HashMap();
        Statement stmt = null;
        ResultSet rset = null;
        try {
            stmt = m_connection.createStatement();
            rset = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rset.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();

            ArrayList cols = new ArrayList();
            for (int i = 0; i < numberOfColumns; i++) {
                String col_name = rsmd.getColumnName(i + 1);
                cols.add(col_name);
            }

            int row_num = 0;
            ArrayList rows = new ArrayList();
            while (rset.next() && (maxRowCount == 0 || row_num < maxRowCount)) {
                row_num++;
                HashMap row = new HashMap();
                for (int i = 0; i < numberOfColumns; i++) {
                    String col_value = rset.getString(i + 1);
                    col_value = (col_value == null) ? "" : col_value;
                    String col_name = ((String) cols.get(i)).toUpperCase();
                    row.put(col_name, col_value);
                }
                rows.add(row);
            }
            rowset.put("ROWSET", rows);

            rset.close();
            stmt.close();

//            System.out.println("successful");
        } catch (SQLException e) {

            System.out.println("### Error ExeSQL at OneValue: " + sql);
            //设置返回值
            rowset = null;
            try {
                if (rset != null)
                    rset.close();
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } finally {
                        stmt.close();
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (!mflag) {
                    ConnectionFactory.closeConnection(m_connection);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return rowset;
    }

    public Vector execSql(String sql) {
        return execSql(sql, 10000);
    }

    public Vector execSql(String sql, int maxRowCount) {
        Vector rowset = new Vector();
        ArrayList arr = execSqltoArr(sql, maxRowCount);
        if (arr != null) {
            for (int i = 0; i < arr.size(); i++) {
                rowset.add(arr.get(i));
            }
        }
        return rowset;
    }

    public ArrayList execSqltoArr(String sql) {
        return execSqltoArr(sql, 10000);
    }

    public ArrayList execSqltoArr(String sql, int maxRowCount) {

        ArrayList rowset = new ArrayList();
        Statement stmt = null;
        ResultSet rset = null;
        try {
            stmt = m_connection.createStatement();
            rset = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rset.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();

            ArrayList cols = new ArrayList();
            for (int i = 0; i < numberOfColumns; i++) {
                String col_name = rsmd.getColumnName(i + 1);
                cols.add(col_name);
            }

            int row_num = 0;
            while (rset.next() && (maxRowCount == 0 || row_num < maxRowCount)) {
                row_num++;
                HashMap row = new HashMap();
                for (int i = 0; i < numberOfColumns; i++) {
                    String col_value = rset.getString(i + 1);
                    col_value = (col_value == null) ? "" : col_value;
                    String col_name = ((String) cols.get(i)).toUpperCase();
                    row.put(col_name, col_value);
                }
                rowset.add(row);
            }

            rset.close();
            stmt.close();

            // System.out.println("successful");
        } catch (SQLException e) {

            System.out.println("### Error ExeSQL at OneValue: " + sql);
            // 设置返回值
            rowset = null;
            try {
                if (rset != null)
                    rset.close();
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } finally {
                        stmt.close();
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (!mflag) {
                    ConnectionFactory.closeConnection(m_connection);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return rowset;
    }

    public ArrayList execSqltoArray(String sql) {
        return execSqltoArray(sql, 10000);
    }

    public ArrayList execSqltoArray(String sql, int maxRowCount) {

        ArrayList rowset = new ArrayList();
        Statement stmt = null;
        ResultSet rset = null;
        try {
            stmt = m_connection.createStatement();
            rset = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rset.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();


            int row_num = 0;
            while (rset.next() && (maxRowCount == 0 || row_num < maxRowCount)) {
                row_num++;
                String[] row = new String[numberOfColumns];
                for (int i = 0; i < numberOfColumns; i++) {
                    String col_value = rset.getString(i + 1);
                    col_value = (col_value == null) ? "" : col_value;
                    row[i] = col_value;
                }
                rowset.add(row);
            }

            rset.close();
            stmt.close();

            // System.out.println("successful");
        } catch (SQLException e) {

            System.out.println("### Error ExeSQL at OneValue: " + sql);
            // 设置返回值
            rowset = null;
            try {
                if (rset != null)
                    rset.close();
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } finally {
                        stmt.close();
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (!mflag) {
                    ConnectionFactory.closeConnection(m_connection);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return rowset;
    }



    /**
     * 把ResultSet中取出的数据转换为相应的数据值字符串
     * 输出：如果成功执行，返回True，否则返回False，并且在Error中设置错误的详细信息
     *
     * @param rsmd ResultSetMetaData
     * @param rs   ResultSet
     * @param i    int
     * @return String
     */
    public String getDataValue(ResultSetMetaData rsmd, ResultSet rs, int i) {
        String strValue = "";

        try {
            int dataType = rsmd.getColumnType(i);
            int dataScale = rsmd.getScale(i);
            int dataPrecision = rsmd.getPrecision(i);
            strValue = rs.getString(i);
            if (strValue == null || "　".equals(strValue) || "null".equals(strValue)) {
                strValue = "";
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return strValue;
    }
}
