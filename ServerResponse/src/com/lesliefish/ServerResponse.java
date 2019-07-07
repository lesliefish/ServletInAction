package com.lesliefish;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ServerResponse extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置刷新自动加载时间为 5 秒
        resp.setIntHeader("Refresh", 5);
        resp.setContentType("text/html;charset=UTF-8");

        Calendar calendar = Calendar.getInstance();
        Date taskTime = calendar.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String nowTime = df.format(taskTime);

        PrintWriter printWriter = resp.getWriter();

        String title = "Auto Refresh Header";
        String docType =
                "<!DOCTYPE html>\n";
        printWriter.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n"+
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<p>当前时间是：" + nowTime + "</p>\n");
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
