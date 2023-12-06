package Servlets.Login;

import java.sql.ResultSet;
import java.sql.SQLException;

public record Login(int user_id, String email, String pwd, String cookies) {
    static Login fromRs(ResultSet rs) throws SQLException {
        int id = rs.getInt("user_id");
        String email = rs.getString("email");
        String pwd = rs.getString("pwd");
        String cookies = rs.getString("cookies");
        return new Login(id, email, pwd, cookies);
    }

}
