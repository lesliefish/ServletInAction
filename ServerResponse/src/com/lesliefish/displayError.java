package com.lesliefish;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/displayError")
public class displayError extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.print("display init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(12345, "测试程序！！错误码12345");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        /doGet(req, resp);
    }
}

/*
HTTP Status 12345 – Unknown Reason
Type Status Report

Message 测试程序！！错误码12345

Description No description available

Apache Tomcat/8.5.42
* */