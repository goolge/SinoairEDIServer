package com.sinoair.iemisgateway.cainiao.action;

import com.sinoair.iemisgateway.cainiao.service.UploadOrderCainiaoService;
import com.sinoair.iemisgateway.util.BaseLogger;
import com.sinoair.iemisgateway.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Created by IntelliJ IDEA.
 * User: WangXX4
 * Date: 15-5-4
 * Time: 下午3:48
 * todo 没有集成测试
 */
public class UploadOrderCainiaoAction extends HttpServlet {
    String logistics_interface;
    String logistic_provider_id;
    String msg_type;
    String data_digest;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BaseLogger.info("UploadOrderCainiaoByHttp.doGet");
        String uri = req.getRequestURI();
        StringBuffer url = req.getRequestURL();
        BaseLogger.info("uri = " + uri);
        BaseLogger.info("url = " + url);
        Enumeration parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = (String) parameterNames.nextElement();
            String parameterValue = req.getParameter(parameterName);
            BaseLogger.info(parameterName + ":" + parameterValue);
        }
        logistics_interface = StringUtil.null2EmptyString(req.getParameter("logistics_interface"));
        UploadOrderCainiaoService uploadOrderCainiaoService = new UploadOrderCainiaoService();
        String result = uploadOrderCainiaoService.uploadOrderCainiao(logistics_interface);
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
