package com.sinoair.iemisgateway.util;

import java.sql.*;

/**
 * Created by IntelliJ IDEA.
 * User: WangXX4
 * Date: 15-5-14
 * Time: 下午1:30
 * To change this template use File | Settings | File Templates.
 */
public class ConnectionFactory {

    public static Connection conn;

    public static void main(String[] args) throws Exception {
        conn = get200Connection();
        Statement statement = conn.createStatement();
        String sql = "select ep.eawb_printcode,eba_occurtime,eba_remark,eba_occurplace,ead_code, east_code\n" +
                "  from eawbpre ep, expressbusinessactivity eba\n" +
                " where ep.eawb_printcode = eba.eawb_printcode\n" +
                "   and (eba.QA is null or eba.QA <> 's')\n" +
                "   and ep.eawb_printcode = '860066428695'";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            String eawb_printcode = resultSet.getString("eawb_printcode");
            String eba_occurtime = resultSet.getString("eba_occurtime");
            String eba_remark = resultSet.getString("eba_remark");
            String eba_occurplace = resultSet.getString("eba_occurplace");
            String ead_code = resultSet.getString("ead_code");
            String east_code = resultSet.getString("east_code");
            System.out.println("eawb_printcode = " + eawb_printcode);
            System.out.println("eba_occurtime = " + eba_occurtime);
            System.out.println("eba_remark = " + eba_remark);
            System.out.println("eba_occurplace = " + eba_occurplace);
            System.out.println("ead_code = " + ead_code);
            System.out.println("east_code = " + east_code);
        }
    }

    public static Connection get200Connection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        conn = DriverManager.getConnection("jdbc:oracle:thin:@172.17.0.200:1521:training",
                "cmis",
                "cmistraining");
        return conn;
    }

    public static Connection getConnectionInProperties() throws ClassNotFoundException, SQLException {
        String connectionURL = PropertiesUtil.readProperty("db","database.jdbc.connectionURL");
        String username = PropertiesUtil.readProperty("db","database.jdbc.username");
        String password = PropertiesUtil.readProperty("db", "database.jdbc.password");
        System.out.println("connectionURL = " + connectionURL);
        return getConnection(connectionURL, username, password);
    }

    public static Connection getConnection(String connectionURL, String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        conn = DriverManager.getConnection(connectionURL,
                username,
                password);
        return conn;
    }

    public static Connection get194Connection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        conn = DriverManager.getConnection("jdbc:oracle:thin:@172.17.0.194:1521:orcl", "cmis", "cmis");
        return conn;
    }
    public static void closeConnection(Connection conn){
            try{
              if(conn!=null){
                   if(!conn.isClosed()){
                       conn.close();
                  }
                }
           }catch(Exception ex){
                ex.printStackTrace();
            }
        }
}
