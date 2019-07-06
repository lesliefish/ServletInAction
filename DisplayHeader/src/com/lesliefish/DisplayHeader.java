package com.lesliefish;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class DisplayHeader extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter printWriter = resp.getWriter();
        String title= "Http Header Request Example";

        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
        printWriter.println(docType + "<html>\n" +
                "<head><title>" + title + "</title></head>\n"+
                "<body bgcolor = \"#f0f0f0\">\n" +
                "<h1 align = \"center\">" + title + "</h1>\n" +
                "<table width = \"100%\" border = \"1\" align = \"center\">\n" +
                "<tr bgcolor = \"#949494\">\n" +
                "<th>Header Name</th><th>Header Value(s)</th>\n"+
                "</tr>\n");

        Enumeration headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String paramName = (String)headerNames.nextElement();
            printWriter.print("<tr><td>" + paramName + "</td>\n");
            String paramValue = req.getHeader(paramName);
            printWriter.println("<td> " + paramValue + "</td></tr>\n");
        }

        printWriter.println("</table>\n</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
