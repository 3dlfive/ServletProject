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
        // http://localhost:8080/messages?id=1
        HashMap<String, Object> data = new HashMap<>();
        data.put("withWho",Integer.parseInt(id));
        try {

            data.put("messages",messages.findAllUsersMessagetoReciver(1, Integer.parseInt(id)));
            try{
                data.put("sender_id",messages.findAllUsersMessagetoReciver(1, Integer.parseInt(id)).get(0).sender_id());
                data.put("sender_name",messages.findAllUsersMessagetoReciver(1, Integer.parseInt(id)).get(0).sender_name());
            } catch (IndexOutOfBoundsException e){
                // for new chat
                data.put("sender_id",1);
                data.put("sender_name",Integer.parseInt(id));
            }


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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getParameter("body");
        String sender_id2 = req.getParameter("sender_id");
        System.out.println(sender_id2);
        int reciver_id = Integer.parseInt(req.getPathInfo().substring(1));
        int sender_id = 1;
        try {
            messages.save(new Message(sender_id,reciver_id,body));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(body);
        this.doGet(req,resp);
    }
}
