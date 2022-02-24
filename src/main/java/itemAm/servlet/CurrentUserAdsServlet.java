package itemAm.servlet;

import itemAm.manager.ItemManager;
import itemAm.model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/currentUserAds")
public class CurrentUserAdsServlet extends HttpServlet {
    private final ItemManager itemManager = new ItemManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userId"));
        List<Item> itemsByUser = itemManager.getCurrentUserAds(userId);

        req.setAttribute("items",itemsByUser);
        req.getRequestDispatcher("/home.jsp").forward(req,resp);
    }
}
