package com.sinoair.iemisgateway.domain;

import com.sinoair.iemisgateway.util.BaseLogger;
import com.sinoair.iemisgateway.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * Created by WangXX4 on 2015/7/29.
 */
public class ExpressEdiLog {
    String eel_id;
    String eel_partner;
    String eel_interface;
    String eel_request;
    String eel_response;
    String eel_is_exception;
    String eel_handle_time;
    String eel_comments;
    static Connection connection = null;
    static PreparedStatement preparedStatement = null;
    public static final String is_exception = "Y";
    public static final String is_not_exception = "N";

    static {
        initConnection();

    }

    private static void initConnection() {
        try {
            connection = ConnectionFactory.getConnectionInProperties();
            String sql = "INSERT INTO EXPRESS_EDI_LOG\n" +
                    "  (EEL_ID,\n" +
                    "   EEL_PARTNER,\n" +
                    "   EEL_INTERFACE,\n" +
                    "   EEL_REQUEST,\n" +
                    "   EEL_RESPONSE,\n" +
                    "   EEL_IS_EXCEPTION,\n" +
                    "   EEL_HANDLE_TIME,\n" +
                    "   EEL_COMMENTS)\n" +
                    "VALUES\n" +
                    "  (SEQ_EXPRESS_EDI_LOG.NEXTVAL,\n" +
                    "   ?,\n" +
                    "   ?,\n" +
                    "   ?,\n" +
                    "   ?,\n" +
                    "   ?,\n" +
                    "   sysdate,\n" +
                    "   ?)";
            preparedStatement = connection.prepareStatement(sql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ExpressEdiLog(String eel_partner, String eel_interface, String eel_request, String eel_response, String eel_is_exception, String eel_comments) {
        this.eel_partner = eel_partner;
        this.eel_interface = eel_interface;
        this.eel_request = eel_request;
        this.eel_response = eel_response;
        this.eel_is_exception = eel_is_exception;
        this.eel_comments = eel_comments;
    }


    public ExpressEdiLog() {
    }

    public void insertRecord(String eel_response, String Eel_is_exception, String eel_comments) {
        this.setEel_response(eel_response);
        this.setEel_is_exception(Eel_is_exception);
        this.setEel_comments(eel_comments);
        this.insertRecord();
    }

    public void insertRecord() {
        BaseLogger.debug("begin insert record");
        try {
            if (connection == null || connection.isClosed()) {
                initConnection();
            }
            int i = 0;
            preparedStatement.setString(++i, eel_partner);
            preparedStatement.setString(++i, eel_interface);
            preparedStatement.setString(++i, eel_request);
            preparedStatement.setString(++i, eel_response);
            preparedStatement.setString(++i, eel_is_exception);
            preparedStatement.setString(++i, eel_comments);
            preparedStatement.executeQuery();
            BaseLogger.debug("insert record done");
        } catch (SQLException e) {
            e.printStackTrace();
            BaseLogger.debug(e.getMessage());
        }
    }


    public String getEel_partner() {
        return eel_partner;
    }

    public void setEel_partner(String eel_partner) {
        this.eel_partner = eel_partner;
    }

    public String getEel_interface() {
        return eel_interface;
    }

    public void setEel_interface(String eel_interface) {
        this.eel_interface = eel_interface;
    }

    public String getEel_request() {
        return eel_request;
    }

    public void setEel_request(String eel_request) {
        this.eel_request = eel_request;
    }

    public String getEel_response() {
        return eel_response;
    }

    public void setEel_response(String eel_response) {
        this.eel_response = eel_response;
    }

    public String getEel_is_exception() {
        return eel_is_exception;
    }

    public void setEel_is_exception(String eel_is_exception) {
        this.eel_is_exception = eel_is_exception;
    }

    public String getEel_comments() {
        return eel_comments;
    }

    public void setEel_comments(String eel_comments) {
        this.eel_comments = eel_comments;
    }
}
