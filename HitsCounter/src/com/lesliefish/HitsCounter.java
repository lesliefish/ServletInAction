package com.lesliefish;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hitsCounter")
public class HitsCounter extends HttpServlet {
    private int hitCount;

    @Override
    public void init() {
        hitCount = 0;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hitCount++;

        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        String title = "total number of hits";

        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

        printWriter.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor = \"#f0f0f0\">\n" +
                "<h1 align = \"center\">" + title + "</h1>\n" +
                "<h2 align = \"center\">" + hitCount + "</h2>\n" +
                "</body></html>"
      );
    }
}
