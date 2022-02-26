package itemAm.servlet;

import itemAm.manager.ItemManager;
import itemAm.model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/item")
public class ItemServlet extends HttpServlet {
    private final ItemManager itemManager = new ItemManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int itemId = Integer.parseInt(req.getParameter("itemId"));

        Item item = itemManager.getItemById(itemId);

        req.setAttribute("item", item);
        req.getRequestDispatcher("/item.jsp").forward(req, resp);
    }
}
