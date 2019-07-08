package com.lesliefish;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 用于请求异常时，转向自定义的界面
 */
public class ServletException extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws javax.servlet.ServletException, IOException {
        Throwable throwable = (Throwable) req.getAttribute("javax.servlet.error.exception");//该属性给出异常产生的信息，信息可被存储，并在存储为 java.lang.Throwable 数据类型后可被分析。
        Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");//该属性给出状态码
        String servletName = (String) req.getAttribute("javax.servlet.error.servlet_name");//该属性给出 Servlet 的名称
        if (servletName == null) {
            servletName = "Unknown";
        }
        String requestUri = (String) req.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        // 设置响应内容类型
        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter out = resp.getWriter();
        String title = "Servlet 异常处理";

        String docType = "<!DOCTYPE html>\n";
        out.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n" + "<body bgcolor=\"#f0f0f0\">\n");
        out.println("<h1>异常信息实例</h1>");
        if (throwable == null && statusCode == null) {
            out.println("<h2>错误信息丢失</h2>");
            out.println("请返回 <a href=\"" + resp.encodeURL("http://localhost:8080/") + "\">主页</a>。");
        } else if (statusCode != null) {
            out.println("错误代码 : " + statusCode);
        } else {
            out.println("<h2>错误信息</h2>");
            out.println("Servlet 名称 : " + servletName + "</br></br>");
            out.println("异常类型 : " + throwable.getClass().getName() + "</br></br>");
            out.println("请求 URI: " + requestUri + "<br><br>");
            out.println("异常信息: " + throwable.getMessage());
        }
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws javax.servlet.ServletException, IOException {
        doGet(req, resp);
    }
}
