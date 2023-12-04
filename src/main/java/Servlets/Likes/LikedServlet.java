package Servlets.Likes;

import Helpers.ResourcesOps;
import Servlets.Users.User;
import Servlets.Users.Users;
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
import java.util.ArrayList;
import java.util.HashMap;

public class LikedServlet extends HttpServlet {
    private final LikedDB likes;

    public LikedServlet(LikedDB likes) {
        this.likes = likes;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
        cfg.setDirectoryForTemplateLoading(new File(ResourcesOps.dirUnsafe("static/templates")));

        HashMap<String, Object> data = new HashMap<>();
        likes.get().forEach(el-> System.out.println(el.name()));


        data.put("rows",likes.getAll());
        System.out.println(data);
        try (PrintWriter w = resp.getWriter()) {
            Template template = cfg.getTemplate("liked.ftl");
            template.process(data, w);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }


}