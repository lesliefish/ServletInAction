package com.lesliefish;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class CheckBox extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter printWriter = resp.getWriter();
        String title = "Reading Check BoxData";
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

        printWriter.println(docType +
                        "<html>\n" +
                        "<head><title>" + title + "</title></head>\n" +
                        "<body bgcolor = \"#f0f0f0\">\n" +
                        "<h1 align = \"center\">" + title + "</h1>\n" +
                        "<ul>\n" +
                        "  <li><b>Maths Flag : </b>: "
                        + req.getParameter("maths") + "\n" +
                        "  <li><b>Physics Flag: </b>: "
                        + req.getParameter("physics") + "\n" +
                        "  <li><b>Chemistry Flag: </b>: "
                        + req.getParameter("chemistry") + "\n" +
                        "</ul>\n" +
                        "</body>" +
                "</html>"
        );


        // 获取所有form data 参数测试
        Enumeration paramNames = req.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            System.out.print(paramName);
            String[] paramValues = req.getParameterValues(paramName);

            if (paramValues.length == 1) {// 读单个值数据
                String paramValue = paramValues[0];
                if (paramValue.length() == 0)
                    System.out.println("No Value");
                else System.out.println(paramValue);
            } else {
                // 多值读取
                for (int i = 0; i < paramValues.length; i++) {
                    System.out.println(paramValues[i]);
                }
                System.out.println("");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
