package itemAm.servlet;

import itemAm.manager.CategoryManager;
import itemAm.manager.ItemManager;
import itemAm.model.Category;
import itemAm.model.Item;
import itemAm.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/")
public class GeneralPageServlet extends HttpServlet {

    private final ItemManager itemManager = new ItemManager();
    private final CategoryManager categoryManager = new CategoryManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int catId = Integer.parseInt(req.getParameter("catId"));
        Category category = categoryManager.getCategoryById(catId);

        List<Item> lastItems = itemManager.getLastItems();
        List<Item> lastItemsByCategory;
        List<Category> categories = categoryManager.getAllCategories();

        if(user == null){
            if(category == null){
                req.setAttribute("items",lastItems);
                req.setAttribute("categories",categories);
                req.getRequestDispatcher("/firstPage.jsp").forward(req,resp);
            }
            else{
                lastItemsByCategory = itemManager.getLastItemsByCategory(category);
                req.setAttribute("items",lastItemsByCategory);
                req.setAttribute("categories",categories);
                req.getRequestDispatcher("/firstPage.jsp").forward(req,resp);
            }
        }
        else{
            if(category == null){
                req.setAttribute("items",lastItems);
                req.setAttribute("categories",categories);
                req.getRequestDispatcher("/home..jsp").forward(req,resp);
            }
            else{
                lastItemsByCategory = itemManager.getLastItemsByCategory(category);
                req.setAttribute("categories",categories);
                req.setAttribute("items",lastItemsByCategory);
                req.getRequestDispatcher("/home..jsp").forward(req,resp);
            }
        }
    }
}
