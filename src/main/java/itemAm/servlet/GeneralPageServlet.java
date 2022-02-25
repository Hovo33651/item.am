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
        String catIdStr = req.getParameter("catId");

        List<Category> allCategories = categoryManager.getAllCategories();
        List<Item> lastItems = itemManager.getLastItems();
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
                req.setAttribute("items",lastItemsByCategory);
                req.getRequestDispatcher("/home.jsp").forward(req,resp);
            }
        }
    }
}
