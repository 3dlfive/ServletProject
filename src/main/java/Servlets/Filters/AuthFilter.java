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
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            Optional<Cookie> cookieOptional = Arrays.stream(cookies)
                    .filter(c -> "UID".equals(c.getName()))
                    .findFirst();

            return cookieOptional.isPresent();
        }

        return false;
    }

    @Override
    public void behaviorIfLogicNotPassed(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/login");
    }
}
