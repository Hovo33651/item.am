package itemAm.servlet;

import itemAm.manager.UserManager;
import itemAm.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    private final UserManager userManager = new UserManager();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String nickname = req.getParameter("nickname");
        StringBuilder msg = new StringBuilder();
        if (name == null || name.length() == 0 || surname == null || surname.length() == 0
                || email == null || email.length() == 0 || password == null || password.length() == 0) {
            msg.append("Խնդրում ենք լրացնել բոլոր * դաշտերը<br>");
            req.getSession().setAttribute("msg", msg);
            resp.sendRedirect("/register.jsp");
        }

        if (msg.toString().equals("")) {
            User user = User.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .password(password)
                    .build();
            if(nickname != null){
                user.setNickname(nickname);
            }

            if (userManager.addUser(user)) {
                resp.sendRedirect("/login.jsp");
            } else {
                resp.sendRedirect("/register.jsp");
            }
        }
    }
}
