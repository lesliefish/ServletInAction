package com.lesliefish;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class ServletExample extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do get");

        // 设置响应内容类型
        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter printWriter = resp.getWriter();
        String title = "过滤测试";
        String docType =
                "<!DOCTYPE html> \n";
        printWriter.println(docType +
                "<html>\n" +
                "<head><meta charset=\"utf-8\"><title>" + title + "</title></head>\n"+
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<table width=\"100%\" border=\"1\" align=\"center\">\n" +
                "<tr bgcolor=\"#949494\">\n" +
                "<th>Header 名称</th><th>Header 值</th>\n"+
                "</tr>\n");

        Enumeration headerNames = req.getHeaderNames();

        while(headerNames.hasMoreElements()) {
            String paramName = (String)headerNames.nextElement();
            printWriter.print("<tr><td>" + paramName + "</td>\n");
            String paramValue = req.getHeader(paramName);
            printWriter.println("<td> " + paramValue + "</td></tr>\n");
        }
        printWriter.println("</table>\n</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do post");
        doGet(req, resp);
    }
}
