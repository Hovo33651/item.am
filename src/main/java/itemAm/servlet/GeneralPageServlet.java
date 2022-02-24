package itemAm.servlet;

import itemAm.manager.ItemManager;
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

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<Item> lastItems = itemManager.getLastItems();
        String catId = req.getParameter("catId");

        if (user == null) {
            req.setAttribute("items",lastItems);
            req.getRequestDispatcher("/first.jsp").forward(req,resp);
        } else {
            List<Item> items;
            if (catId == null) {
                req.setAttribute("items",lastItems);
                req.getRequestDispatcher("/home.jsp").forward(req,resp);
            } else if (catId.equals("car")){
                items = itemManager.getLastItemsByCategory("CAR");
                req.setAttribute("items",items);
                req.getRequestDispatcher("/home.jsp").forward(req,resp);
            }
            else if(catId.equals("house")){
                items = itemManager.getLastItemsByCategory("HOUSE");
                req.setAttribute("items",items);
                req.getRequestDispatcher("/home.jsp").forward(req,resp);
            }
            else if(catId.equals("commercial")){
                items = itemManager.getLastItemsByCategory("COMMERCIAL");
                req.setAttribute("items",items);
                req.getRequestDispatcher("/home.jsp").forward(req,resp);
            }
            else if(catId.equals("furniture")){
                items = itemManager.getLastItemsByCategory("FURNITURE");
                req.setAttribute("items",items);
                req.getRequestDispatcher("/home.jsp").forward(req,resp);
            }
        }
    }
}
