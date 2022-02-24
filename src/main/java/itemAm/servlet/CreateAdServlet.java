package itemAm.servlet;

import itemAm.manager.CategoryManager;
import itemAm.manager.ItemManager;
import itemAm.manager.UserManager;
import itemAm.model.Category;
import itemAm.model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = "/createAd")
public class CreateAdServlet extends HttpServlet {

    private final String UPLOAD_DIR = "C:\\Users\\Hovhanes Gevorgyan\\IdeaProjects\\Autho.am\\upload";
    private final ItemManager itemManager = new ItemManager();
    private final UserManager userManager = new UserManager();
    private final CategoryManager categoryManager = new CategoryManager();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        Category category = categoryManager.getCategoryById(Integer.parseInt(req.getParameter("catId")));
        double price = Double.parseDouble(req.getParameter("price"));
        String currency = req.getParameter("currency");

        int userId = Integer.parseInt(req.getParameter("userId"));

       Item item = Item.builder()
               .title(title)
               .description(description)
               .category(category)
               .price(price)
               .currency(currency)
               .user(userManager.getUserById(userId))
               .build();

        for (Part part : req.getParts()) {
            if (getFileName(part) != null) {
                String fileName = System.currentTimeMillis() + getFileName(part);
                String fullFileName = UPLOAD_DIR + File.separator + fileName;
                part.write(fullFileName);
                item.setPicUrl(fileName);
            }
        }
        if (itemManager.addItem(item)) {
            resp.sendRedirect("/");
        }

    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return null;
    }
}
