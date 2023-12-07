import Helpers.Database;

import Servlets.Filters.AuthFilter;
import Servlets.Likes.LikedDBDao;
import Servlets.Likes.LikedServlet;
import Servlets.Login.LoginService;
import Servlets.Login.LoginServlet;
import Servlets.Messages.MessagesDao;
import Servlets.Messages.MessagesServlet;
import Servlets.RedirectServlet;
import Servlets.StylesServlet;
import Servlets.Users.DaoUsersSQL;
import Servlets.Users.UsersServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.sql.Connection;
import java.util.EnumSet;

public class App {
        public static void main(String[] args) throws Exception {
            Server server = new Server(8080);
            ServletContextHandler handler = new ServletContextHandler();
            //DB conection
            Database db = new Database("jdbc:postgresql://postgres:5432/tinder_step","postgres","pg123456");
            Connection conn = db.mkConn();

            EnumSet<DispatcherType> tpe = EnumSet.of(DispatcherType.REQUEST);



            //Servlets
            handler.addServlet("Servlets.HelloServlet", "/hello");
            LikedDBDao selData = new LikedDBDao(conn);

            {
                DaoUsersSQL users = new DaoUsersSQL(conn);
                UsersServlet usersServlet = new UsersServlet(users, selData);
                handler.addServlet(new ServletHolder(usersServlet), "/users");
                handler.addFilter(AuthFilter.class,"/users",tpe);
            }
            {
                LikedServlet liked = new LikedServlet(selData);
                handler.addServlet(new ServletHolder(liked), "/liked");
                handler.addFilter(AuthFilter.class,"/liked",tpe);
            }
            {
                MessagesServlet chat = new MessagesServlet(new MessagesDao(conn));
                handler.addServlet(new ServletHolder(chat), "/messages/*");
                handler.addFilter(AuthFilter.class,"/messages/*",tpe);
            }
            {

                LoginServlet login = new LoginServlet(new LoginService(conn));
                handler.addServlet(new ServletHolder(login), "/login");

            }
            {
                    handler.addServlet(new ServletHolder(new StylesServlet("static")), "/static/*");



            }
            handler.addServlet(new ServletHolder(new RedirectServlet("/login")), "/*");


            server.setHandler(handler);
            server.start();
            server.join();
        }

}
