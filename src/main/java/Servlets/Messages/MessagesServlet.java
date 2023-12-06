package Servlets.Messages;

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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

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
        Optional<Cookie> cookcies = Arrays.stream(req.getCookies()).filter(c->c.getName().equals("UID")).findFirst();
        String cookies = cookcies.get().getValue();

        try {
            Optional<Integer> senders_iid = messages.reciveSenderId(cookies);
            System.out.println(senders_iid.get());
            data.put("messages",messages.findAllUsersMessagetoReciver(senders_iid.get(), Integer.parseInt(id)));
            try{
                data.put("sender_id",messages.findAllUsersMessagetoReciver(senders_iid.get(), Integer.parseInt(id)).get(0).sender_id());
                data.put("sender_name",messages.findAllUsersMessagetoReciver(senders_iid.get(), Integer.parseInt(id)).get(0).sender_name());
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
        int reciver_id = Integer.parseInt(req.getPathInfo().substring(1));
        String cookies = Arrays.stream(req.getCookies()).filter(c->c.getName().equals("UID")).findFirst().get().getValue();

        try {
            Optional<Integer> sender_id  = messages.reciveSenderId(cookies);
            messages.save(new Message(sender_id.get(),reciver_id,body));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.doGet(req,resp);
    }
}
