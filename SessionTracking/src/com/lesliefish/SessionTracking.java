package com.lesliefish;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/session")
public class SessionTracking extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 创建一个httpsession对象
        HttpSession httpSession = req.getSession(true);
        // 创建时间
        Date createTime = new Date(httpSession.getCreationTime());
        //// 获取该网页的最后一次访问时间
        Date lastAccessTime = new Date(httpSession.getLastAccessedTime());

        // 设置日期输出格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String title = "Servlet Session";

        Integer visitCount = new Integer(0);
        String visitCountKey = new String("visitCount");
        String userIDKey = new String("userID");
        String userID = new String("lesliefish");

        // 检查是否新访问者
        if (httpSession.isNew()) {
            httpSession.setAttribute(userIDKey, userID);
        } else {
            visitCount = (Integer) httpSession.getAttribute(visitCountKey);
            if (visitCount == null){
                visitCount = 0;
            }
            visitCount = visitCount + 1;

            String sessionUserId = (String)httpSession.getAttribute(userIDKey);
            if (sessionUserId != null){
                userID = sessionUserId;
            }
        }

        httpSession.setAttribute(visitCountKey, visitCount);
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

        out.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n" + "<body bgcolor = \"#f0f0f0\">\n" + "<h1 align = \"center\">"
                + title + "</h1>\n" + "<h2 align = \"center\">Session Infomation</h2>\n" + "<table border = \"1\" align = \"center\">\n"
                + "<tr bgcolor = \"#949494\">\n" + "  <th>Session info</th><th>value</th> </tr>\n " + "<tr>\n" + "  <td>id</td>\n" +
                "  <td>" + httpSession.getId() + "</td> </tr >\n " + "<tr>\n" + "  <td>Creation Time</td>\n" + "  <td>" + createTime +
                "  </td> </tr >\n " + "<tr>\n" + "  <td>Time of Last Access</td>\n" + "  <td>" + lastAccessTime
                + "  </td> </tr >\n " + "<tr>\n" + "  <td>User ID</td>\n" + "  <td>" + userID + "  </td> </tr >\n " + "<tr>\n"
                + "  <td>Number of visits</td>\n" + "  <td>" + visitCount + "</td> </tr>\n " + "</table>\n" + "</body> </html > ");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
