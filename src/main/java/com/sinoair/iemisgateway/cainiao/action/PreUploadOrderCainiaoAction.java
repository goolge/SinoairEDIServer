package com.sinoair.iemisgateway.cainiao.action;

import com.sinoair.iemisgateway.cainiao.service.PreUploadOrderCainiaoService;
import com.sinoair.iemisgateway.util.BaseLogger;
import com.sinoair.iemisgateway.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by WangXX4 on 2015/7/8.
 */
public class PreUploadOrderCainiaoAction extends HttpServlet {
    String logistics_interface;
    String logistic_provider_id;
    String msg_type;
    String data_digest;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logistics_interface = StringUtil.null2EmptyString(req.getParameter("logistics_interface"));
        PreUploadOrderCainiaoService preUploadOrderCainiaoService = new PreUploadOrderCainiaoService();
        String result = preUploadOrderCainiaoService.preUploadOrderCainiao(logistics_interface);
        BaseLogger.info("result = " + result);
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.print(result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BaseLogger.info("UploadOrderCainiaoByHttp.doPost");
        this.doGet(req, resp);
    }
}
