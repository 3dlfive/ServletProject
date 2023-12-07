package Servlets;

import Helpers.ResourcesOps;
import freemarker.template.Configuration;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;
import java.util.stream.Stream;

public class StylesServlet extends HttpServlet {
    private final String root;

    public StylesServlet(String root) {
        this.root = root;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String prefix = ResourcesOps.dirUnsafe(root);

        String fileName = req.getPathInfo();
        String fullName = prefix + fileName;
        String fpath ="/"+ root+fileName;


        String osName = System.getProperty("os.name");


        if (osName.contains("Windows")){
            Path path = Paths.get(fullName.substring(1)); //windows
            Stream<String> lines = Files.lines(path);

            try (PrintWriter w = resp.getWriter()) {
                lines.forEach(w::println);
            }
        } else{


            try (InputStream inputStream = this.getClass().getResourceAsStream(fpath)) {
                try (Stream<String> lines = new Scanner(inputStream).useDelimiter("\\A").tokens()) {
                    // Process the lines stream as needed
                    try (PrintWriter w = resp.getWriter()) {
                        lines.forEach(w::println);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

}
