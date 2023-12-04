package Servlets.Users;

import Servlets.SelectedUsers.SelectedDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class UsersServlet extends HttpServlet {
    private final DaoUsersList users;
    private final SelectedDB selected;
    private int counter = 0;
    public UsersServlet(DaoUsersList users, SelectedDB selected) {
        this.users = users;
        this.selected = selected;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter w = resp.getWriter()) {
            w.println("<html><body><table>");
//            users.get().forEach(x -> w.println(x.toHtml()));
            w.println(users.find(counter).get().toHtml());
            w.println("</table></body></html>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Boolean decision = Boolean.parseBoolean(req.getParameter("des_button"));
        String name = req.getParameter("name");
        selected.put(users.findFirst(name).get());

        if(counter == users.size()-1){counter=0;} else {counter++;}

        this.doGet(req,resp);
    }
}
