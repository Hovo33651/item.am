package itemAm.servlet;

import itemAm.manager.UserManager;
import itemAm.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private final UserManager userManager = new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        StringBuilder msg = new StringBuilder();

        if (password == null || password.length() == 0 || username == null || username.length() == 0) {
            msg.append("Խնդրում ենք լրացնել բոլոր դաշտերը");
            req.getSession().setAttribute("msg", msg);
            resp.sendRedirect("/login.jsp");
        } else {
            User user;
            if (username.contains("@mail") || username.contains("@gmail") || username.contains("@yandex")) {
                user = userManager.getUserByEmail(username);
            } else {
                user = userManager.getUserByNickname(username);
            }
            checkUser(req, resp, password, msg, user);
        }
    }

    private void checkUser(HttpServletRequest req, HttpServletResponse resp, String password, StringBuilder msg, User user) throws IOException {
        if (user == null || !user.getPassword().equals(password)) {
            msg.append("Սխալ էլ․ հասցե կամ գաղտնաբառ");
            req.getSession().setAttribute("msg", msg);
            resp.sendRedirect("/login.jsp");
        } else {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/main");
        }
    }
}
