package com.lesliefish;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/uploadFile")
public class UploadFile extends HttpServlet {
    private static final long serialVersionUID = 1L;
    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "upload";

    // 上传配置
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!ServletFileUpload.isMultipartContent(req)) {
            PrintWriter printWriter = resp.getWriter();
            printWriter.println("Error: form data must set enctype=multipart/form-data");
            printWriter.flush();
            return;
        }

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        diskFileItemFactory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时目录
        diskFileItemFactory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
        upload.setFileSizeMax(MAX_FILE_SIZE);// 文件最大值
        upload.setSizeMax(MAX_REQUEST_SIZE);// 单次最大接收值
        upload.setHeaderEncoding("utf-8");

        String uploadFilePath = req.getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;

        // 如果目录不存在则创建
        File uploadDir = new File(uploadFilePath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            List<FileItem> fileItems = upload.parseRequest(req);
            if (fileItems != null && fileItems.size() > 0) {
                for (FileItem item : fileItems) {
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadFilePath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        // 在控制台输出文件的上传路径
                        System.out.println(filePath);
                        // 保存文件到硬盘
                        item.write(storeFile);
                        req.setAttribute("message", "Success!");
                    }
                }
            }
        } catch (Exception ex) {
            req.setAttribute("message", "error message: " + ex.getMessage());
        }

        req.getServletContext().getRequestDispatcher("/message.jsp").forward(req, resp);
    }
}
