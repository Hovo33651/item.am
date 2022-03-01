package itemAm.servlet;

import itemAm.manager.CategoryManager;
import itemAm.manager.ItemManager;
import itemAm.manager.ItemPicRelatTableManager;
import itemAm.manager.PictureManager;
import itemAm.model.Category;
import itemAm.model.Item;
import itemAm.model.Picture;
import itemAm.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = "/createAd")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class CreateAdServlet extends HttpServlet {

    private final ItemManager itemManager = new ItemManager();
    private final CategoryManager categoryManager = new CategoryManager();
    private final PictureManager pictureManager = new PictureManager();
    private final ItemPicRelatTableManager itemPicRelatTableManager = new ItemPicRelatTableManager();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        StringBuilder msg = new StringBuilder();
        try {
            Collection<Part> parts = req.getParts();
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            Category category = categoryManager.getCategoryById(Integer.parseInt(req.getParameter("catId")));
            double price = Double.parseDouble(req.getParameter("price"));
            String currency = req.getParameter("currency");

            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");

            Item item = Item.builder()
                    .title(title)
                    .description(description)
                    .category(category)
                    .price(price)
                    .currency(currency)
                    .user(user)
                    .build();

            int itemId = itemManager.addItem(item);
            item.setId(itemId);
            List<Integer> picIds = new ArrayList<>();
            for (Part part : parts) {
                if (getFileName(part) != null) {
                    if (Objects.equals(getFileName(part), "")) {
                        item.setPictures(null);
                        break;
                    }
                    String fileName = System.currentTimeMillis() + getFileName(part);
                    String UPLOAD_DIR = "C:\\Users\\Hovhanes Gevorgyan\\IdeaProjects\\Autho.am\\upload";
                    String fullFileName = UPLOAD_DIR + File.separator + fileName;
                    part.write(fullFileName);
                    Picture picture = Picture.builder()
                            .picUrl(fileName)
                            .build();
                    int picId = pictureManager.addPic(picture);
                    if (itemPicRelatTableManager.addItemPic(itemId, picture.getId())) {
                        picIds.add(picId);
                    }
                }
            }
            List<Picture> picturesById = pictureManager.getPicturesById(picIds);
            if (!picturesById.isEmpty()) {
                item.setPictures(picturesById);
            }
            resp.sendRedirect("/main");

        } catch (IllegalStateException e) {
            msg.append("Միավոր նկարի չափը չպետք է գերազանցի 5MB-ը");
            req.setAttribute("msg", msg);
            req.getRequestDispatcher("/createAd.jsp").forward(req, resp);
        }

    }
        private String getFileName (Part part){
            for (String content : part.getHeader("content-disposition").split(";")) {
                if (content.trim().startsWith("filename"))
                    return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
            return null;
        }
    }

