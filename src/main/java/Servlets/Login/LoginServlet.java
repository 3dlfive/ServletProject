package Servlets.Login;

import Helpers.ResourcesOps;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class LoginServlet extends HttpServlet {
    private final LoginService logindata;
    String status = null;
    public LoginServlet(LoginService logindata) {
        this.logindata = logindata;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
        cfg.setDirectoryForTemplateLoading(new File(ResourcesOps.dirUnsafe("static/templates")));

        HashMap<String, Object> data = new HashMap<>();
        data.put("status",status);

        try (PrintWriter w = resp.getWriter()) {
            Template template = cfg.getTemplate("login.ftl");
            template.process(data, w);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pwd = req.getParameter("password");
        try {
            Optional<Login> l = logindata.findByUserandPassword(login,pwd);
            if (l.isEmpty()) {
                this.status = "Login failed. Cred not found";
                resp.sendRedirect("/login");
            }else {
                UUID uuid = UUID.randomUUID();
                logindata.insertCookies(new Login(l.get().user_id(),uuid)); // save to db
                resp.addCookie(new Cookie("UID",String.valueOf(uuid))); // save cookies to user
                resp.sendRedirect("/users");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }


}
