package Servlets.Users;

import Helpers.ResourcesOps;
import Servlets.Likes.Action;
import Servlets.Likes.LikedDB;
import Servlets.Likes.LikedDBDao;
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
    private final DaoUsersSQL users;
    private final LikedDBDao likes;
    private int counter = 1;
    public UsersServlet(DaoUsersSQL users, LikedDBDao likes) {
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
        int id = Integer.parseInt(req.getParameter("des_button").substring(req.getParameter("des_button").indexOf(",")+1));

        if (decision) {
            try {
                likes.save(new Action(1,id,"liked"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                likes.save(new Action(1,id,"unliked"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            if(counter == users.size()){counter=1;
                resp.sendRedirect("/liked");
            } else {
                counter++;
                this.doGet(req,resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
