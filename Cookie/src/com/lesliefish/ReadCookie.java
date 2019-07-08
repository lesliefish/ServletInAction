package com.lesliefish;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

@WebServlet("/cookie/read")
public class ReadCookie extends HttpServlet {
    public ReadCookie() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = null;
        Cookie[] cookies = null;

        // 获取与该域有关的Cookie数组
        cookies = req.getCookies();

        // 设置相应内容类型
        resp.setContentType("text/html;charset = UTF-8");

        PrintWriter printWriter = resp.getWriter();
        String title = "Reading Cookies Example";
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

        printWriter.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n" + "<body bgcolor = \"#f0f0f0\">\n");

        if (cookies != null) {
            printWriter.println("<h2> Found Cookies Name and Value</h2>");

            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                printWriter.print("Name : " + URLDecoder.decode(cookie.getName(), "utf-8") + ",  ");
                printWriter.print("Value: " + URLDecoder.decode(cookie.getValue(), "utf-8") + " <br/>");
            }
        } else {
            printWriter.println("<h2>No cookies founds</h2>");
        }
        printWriter.println("</body>");
        printWriter.println("</html>");
    }
}
