package Helpers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


public class CookiesHelper {
    final HttpServletResponse resp;
    final HttpServletRequest req;
    final UUID cookie;

    public CookiesHelper(HttpServletResponse resp, HttpServletRequest req, UUID cookie) {
        this.resp = resp;
        this.req = req;
        this.cookie = cookie;
    }

    public void setCookies(){
        Cookie cookiee =new Cookie("UID",String.valueOf(cookie));
        cookiee.setMaxAge(1800);
        resp.addCookie(cookiee);
    }
//
}
