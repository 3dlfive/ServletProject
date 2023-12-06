package Servlets.Login;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public record Login(int user_id, String email, String pwd, UUID cookies, String status) {
    public Login(int user_id, UUID cookies) {
        this(user_id, null, null, cookies,null);
    }

    public Login(int user_id, String email, String pwd) {
        this(user_id, email, pwd, null,null);
    }
    public Login(int user_id,  String pwd) {
        this(user_id, null, pwd, null,null);
    }
    public Login(int user_id, String email, String pwd,UUID cookies) {
        this(user_id, email, pwd, cookies,null);
    }

    static Login fromRs(ResultSet rs) throws SQLException {
        int id = rs.getInt("user_id");
        String email = rs.getString("email");
        String pwd = rs.getString("pwd");
        UUID cookies = UUID.fromString(rs.getString("cookies"));
        return new Login(id, email, pwd, cookies);
    }
    static Login fromRsWithioutCookies(ResultSet rs) throws SQLException {
        int id = rs.getInt("user_id");
        String email = rs.getString("email");
        String pwd = rs.getString("pwd");

        return new Login(id, email, pwd);
    }

}
