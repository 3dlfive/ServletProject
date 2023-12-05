package Servlets;

import Helpers.ResourcesOps;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        Path path = Paths.get(fullName.substring(1)); //windows , linux mojet ne vzletet
        Stream<String> lines = Files.lines(path);

        try (PrintWriter w = resp.getWriter()) {
            lines.forEach(w::println);
        }


    }

}
