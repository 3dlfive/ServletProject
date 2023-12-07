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
import java.io.PrintWriter;
import java.net.URL;
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
        System.out.println("prefix old code:" + prefix);
        System.out.println("path_old_code " + this.getClass().getClassLoader().getResource(root));
        String prefixLinux = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()+"/"+root;
        System.out.println("prefixLinux"+ prefixLinux);
        String prefixLinux2 = this.getClass().getClassLoader().getResource(root).getPath();
        System.out.println("prefixLinux2 "+prefixLinux2);

        String fileName = req.getPathInfo();
        System.out.println("filename"+ fileName);
        String fullName = prefixLinux2 + fileName;
        String fullName2 = "./static/" + fileName;
        System.out.println("fully "+ fullName);
        System.out.println("fully2 "+ fullName);

        URL urlApplicationContext = this.getClass().getClassLoader().getResource(root);
        if (urlApplicationContext != null) {
            System.out.println("url "+ urlApplicationContext.getPath());
        } else {
            throw new RuntimeException("Cannot find file");
        }

        String fullName3 =urlApplicationContext.getPath() + fileName;
        System.out.println("sub pref2"+"."+prefixLinux2.substring(5));
        Path path2 = Paths.get(fullName.substring(1)); //windows
        Path path = Paths.get("."+prefixLinux2.substring(5)+fileName); //Linux
        Stream<String> lines = Files.lines(path);

        try (PrintWriter w = resp.getWriter()) {
            lines.forEach(w::println);
        }


    }

}
