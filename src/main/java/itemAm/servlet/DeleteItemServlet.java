package itemAm.servlet;

import itemAm.manager.ItemManager;
import itemAm.manager.PictureManager;
import itemAm.model.Picture;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/deleteItem")
public class DeleteItemServlet extends HttpServlet {
    private final ItemManager itemManager = new ItemManager();
    private final PictureManager pictureManager = new PictureManager();



    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int itemId = Integer.parseInt(req.getParameter("itemId"));
        List<Picture> pictures = itemManager.getItemById(itemId).getPictures();
        for (Picture picture : pictures) {
            pictureManager.deletePicById(picture.getId());
        }
        if (itemManager.deleteItemById(itemId)) {
            resp.sendRedirect("/main");
        }
    }
}
