package itemAm.servlet;

import itemAm.manager.ItemManager;
import itemAm.manager.ItemPicRelatTableManager;
import itemAm.manager.PictureManager;
import itemAm.model.Item;
import itemAm.model.Picture;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/item")
public class ItemServlet extends HttpServlet {
    private final ItemManager itemManager = new ItemManager();
    private final ItemPicRelatTableManager itemPicRelatTableManager = new ItemPicRelatTableManager();
    private final PictureManager pictureManager = new PictureManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int itemId = Integer.parseInt(req.getParameter("itemId"));
        List<Integer> picIdsByItemId = itemPicRelatTableManager.getPicIdsByItemId(itemId);
        List<Picture> picturesById = pictureManager.getPicturesById(picIdsByItemId);

        Item item = itemManager.getItemById(itemId);
        item.setPictures(picturesById);


        req.setAttribute("item", item);
        req.getRequestDispatcher("/item.jsp").forward(req, resp);
    }
}
