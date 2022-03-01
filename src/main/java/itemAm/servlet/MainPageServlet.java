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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/main")
public class MainPageServlet extends HttpServlet {

    private final ItemManager itemManager = new ItemManager();
    private final CategoryManager categoryManager = new CategoryManager();
    private final ItemPicRelatTableManager itemPicRelatTableManager = new ItemPicRelatTableManager();
    private final PictureManager pictureManager = new PictureManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String catIdStr = req.getParameter("catId");

        List<Category> allCategories = categoryManager.getAllCategories();
        List<Item> lastItems = itemManager.getLastItems();
        for (Item item : lastItems) {
            List<Integer> picIds = itemPicRelatTableManager.getPicIdsByItemId(item.getId());
            List<Picture> picturesById = pictureManager.getPicturesById(picIds);
            item.setPictures(picturesById);
        }
        session.setAttribute("categories",allCategories);
        if(user == null){
            if(catIdStr == null){
                req.setAttribute("items",lastItems);
                req.getRequestDispatcher("/firstPage.jsp").forward(req,resp);
            }else{
                List<Item> lastItemsByCategory = itemManager.getLastItemsByCategory(categoryManager.getCategoryById(Integer.parseInt(catIdStr)));
                req.setAttribute("items",lastItemsByCategory);
                req.getRequestDispatcher("/firstPage.jsp").forward(req,resp);
            }
        }else{
            if(catIdStr == null){
                req.setAttribute("items",lastItems);
                req.getRequestDispatcher("/home.jsp").forward(req,resp);
            }else{
                List<Item> lastItemsByCategory = itemManager.getLastItemsByCategory(categoryManager.getCategoryById(Integer.parseInt(catIdStr)));
                for (Item item : lastItemsByCategory) {
                    List<Integer> picIds = itemPicRelatTableManager.getPicIdsByItemId(item.getId());
                    List<Picture> picturesById = pictureManager.getPicturesById(picIds);
                    item.setPictures(picturesById);
                }
                req.setAttribute("items",lastItemsByCategory);
                req.getRequestDispatcher("/home.jsp").forward(req,resp);
            }
        }
    }
}
