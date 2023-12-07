package Servlets.Filters;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class AuthFilter implements HttpFilter{
    @Override
    public boolean myCheckLogic(HttpServletRequest request) {
        Optional<Cookie> cookies = Arrays.stream(request.getCookies()).filter(c->c.getName().equals("UID")).findFirst();
        return cookies.isPresent();
    }

    @Override
    public void behaviorIfLogicNotPassed(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/login");
    }
}
