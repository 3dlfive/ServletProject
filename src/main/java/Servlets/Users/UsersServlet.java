package Servlets.Users;

import Helpers.ResourcesOps;
import Servlets.Likes.LikedDB;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

public class UsersServlet extends HttpServlet {
    private final DaoUsersList users;
    private final LikedDB likes;
    private int counter = 0;
    public UsersServlet(DaoUsersList users, LikedDB likes) {
        this.users = users;
        this.likes = likes;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try (PrintWriter w = resp.getWriter()) {
//            w.println("<html><body><table>");
////            users.get().forEach(x -> w.println(x.toHtml()));
//            w.println(users.find(counter).get().toHtml());
//            w.println("</table></body></html>");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

            Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
            cfg.setDirectoryForTemplateLoading(new File(ResourcesOps.dirUnsafe("static/templates")));
            HashMap<String, Object> data = new HashMap<>();
        try {
            data.put("user",users.find(counter).get());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (PrintWriter w = resp.getWriter()) {
            Template template = cfg.getTemplate("profile.ftl");
            template.process(data, w);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean decision = Boolean.parseBoolean(req.getParameter("des_button").substring(0,req.getParameter("des_button").indexOf(",")));
        System.out.println(decision);
        String name = req.getParameter("des_button").substring(req.getParameter("des_button").indexOf(",")+1);
        System.out.println(name);
        if (decision) likes.put(users.findFirst(name).get());

        if(counter == users.size()-1){counter=0;
            resp.sendRedirect("/liked");
        } else {
            counter++;
            this.doGet(req,resp);
        }


    }
}
