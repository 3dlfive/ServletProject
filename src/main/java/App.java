import Helpers.Database;

import Servlets.Likes.LikedDBDao;
import Servlets.Likes.LikedServlet;
import Servlets.Messages.Message;
import Servlets.Messages.MessagesDao;
import Servlets.Messages.MessagesServlet;
import Servlets.StylesServlet;
import Servlets.Users.DaoUsersSQL;
import Servlets.Users.UsersServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.sql.Connection;

public class App {
        public static void main(String[] args) throws Exception {
            Server server = new Server(8080);
            ServletContextHandler handler = new ServletContextHandler();
            //DB conection
            Database db = new Database("jdbc:postgresql://localhost:5432/tinder_step","postgres","pg123456");
            Connection conn = db.mkConn();

            //Servlets
            handler.addServlet("Servlets.HelloServlet", "/hello");
            LikedDBDao selData = new LikedDBDao(conn);

            {
                DaoUsersSQL users = new DaoUsersSQL(conn);
                UsersServlet usersServlet = new UsersServlet(users, selData);
                handler.addServlet(new ServletHolder(usersServlet), "/users");
            }
            {
                LikedServlet liked = new LikedServlet(selData);
                handler.addServlet(new ServletHolder(liked), "/liked");

            }
            {
                MessagesServlet chat = new MessagesServlet(new MessagesDao(conn));
                handler.addServlet(new ServletHolder(chat), "/messages/*");

            }
            {
                    handler.addServlet(new ServletHolder(new StylesServlet("static")), "/static/*");


            }

            server.setHandler(handler);
            server.start();
            server.join();
        }

}
