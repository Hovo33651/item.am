package itemAm.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet(urlPatterns = "/image")
public class ImageDownloadServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path = req.getParameter("path");
        if (path == null || path.length() == 0) {
            resp.sendRedirect("/main");
        }

        String UPLOAD_DIR = "C:\\Users\\Hovhanes Gevorgyan\\IdeaProjects\\Autho.am\\upload";
        if (!new File(UPLOAD_DIR + File.separator + path).exists()) {
            resp.sendRedirect("/main");
        } else {
            resp.setContentType("image/jpeg");
            resp.setHeader("Content-disposition", "attachment; filename=" + path);

            try (InputStream in = Files.newInputStream(Paths.get(UPLOAD_DIR + File.separator + path));
                 OutputStream out = resp.getOutputStream()) {
                byte[] buffer = new byte[1048];
                int numBytesRead;
                while ((numBytesRead = in.read(buffer)) > 0) {
                    out.write(buffer, 0, numBytesRead);
                }
            }
        }
    }
}
