package com.lesliefish;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

public class FormData extends HttpServlet {
    public static final long serialVersionUID = 1L;

    public FormData() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookieName = new Cookie("name", URLEncoder.encode(req.getParameter("name"), "UTF-8"));
        Cookie cookieUrl = new Cookie("url", URLEncoder.encode(req.getParameter("url"), "UTF-8"));
        //设置过期时间
        cookieName.setMaxAge(60 * 60 * 24);
        cookieName.setPath("/");
        cookieUrl.setMaxAge(60 * 60 * 24);
        cookieUrl.setPath("/");

        // 响应头添加两个cookie
        resp.addCookie(cookieName);
        resp.addCookie(cookieUrl);

        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter printWriter = resp.getWriter();
        String title = "设置 Cookie 实例";
        String docType = "<!DOCTYPE html>\n";
        printWriter.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n"
                + "<body bgcolor=\"#f0f0f0\">\n" + "<h1 align=\"center\">" + title + "</h1>\n" + "<ul>\n"
                + "  <li><b>站点名：</b>：" + req.getParameter("name") + "\n</li>" + "  <li><b>站点 URL：</b>："
                + req.getParameter("url") + "\n</li>" + "</ul>\n" + "</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
