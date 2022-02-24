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

@WebServlet(urlPatterns = "/currentUserAds")
public class CurrentUserAdsServlet extends HttpServlet {
    private final ItemManager itemManager = new ItemManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user =(User) session.getAttribute("user");
        List<Item> itemsByUser = itemManager.getCurrentUserAds(user.getId());

        req.setAttribute("items",itemsByUser);
        req.getRequestDispatcher("/home..jsp").forward(req,resp);
    }
}
