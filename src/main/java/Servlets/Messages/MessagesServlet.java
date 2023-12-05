package Servlets.Messages;

import Helpers.ResourcesOps;
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

public class MessagesServlet extends HttpServlet {
    private final MessagesDao messages;

    public MessagesServlet(MessagesDao messages) {
        this.messages = messages;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
        cfg.setDirectoryForTemplateLoading(new File(ResourcesOps.dirUnsafe("static/templates")));
        String id = req.getPathInfo().substring(1);
        System.out.println(id);
        // http://localhost:8080/messages?id=1
        HashMap<String, Object> data = new HashMap<>();
        data.put("withWho",Integer.parseInt(id));
        try {
            data.put("messages",messages.findAllUsersMessagetoReciver(1, Integer.parseInt(id)));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (PrintWriter w = resp.getWriter()) {
            Template template = cfg.getTemplate("chat.ftl");
            template.process(data, w);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }

    }
}
