import Servlets.Likes.LikedDB;
import Servlets.Likes.LikedServlet;
import Servlets.Users.DaoUsersList;
import Servlets.Users.User;
import Servlets.Users.UsersServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class App {
        public static void main(String[] args) throws Exception {
            Server server = new Server(8080);
            ServletContextHandler handler = new ServletContextHandler();

            handler.addServlet("Servlets.HelloServlet", "/hello");
            LikedDB selData = new LikedDB();
            {
                DaoUsersList users = new DaoUsersList();
                users.put(new User("Kateryna", "https://images.unsplash.com/photo-1601288496920-b6154fe3626a?q=80&w=1826&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
                users.put(new User("Olek", "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?q=80&w=2574&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
                users.put(new User("Anna", "https://images.unsplash.com/photo-1508214751196-bcfd4ca60f91?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
                users.put(new User("Polina", "https://images.unsplash.com/photo-1520529277867-dbf8c5e0b340?q=80&w=2572&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
                UsersServlet usersServlet = new UsersServlet(users, selData);
                handler.addServlet(new ServletHolder(usersServlet), "/users");
            }
            {

                LikedServlet liked = new LikedServlet(selData);
                handler.addServlet(new ServletHolder(liked), "/liked");

            }
            server.setHandler(handler);
            server.start();
            server.join();
        }

}
