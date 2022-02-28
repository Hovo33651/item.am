package itemAm.servlet;

import itemAm.manager.ItemManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteItem")
public class DeleteItemServlet extends HttpServlet {
    private final ItemManager itemManager = new ItemManager();



    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int itemId = Integer.parseInt(req.getParameter("itemId"));

        if (itemManager.deleteItemById1(itemId)) {
            resp.sendRedirect("/main");
        }
    }
}
