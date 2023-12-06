package Servlets.Login;

import Servlets.Messages.Message;
import Servlets.Users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoginService implements LoginDAO<Login>{
    private final Connection conn;

    public LoginService(Connection conn) {
        this.conn = conn;
    }
    @Override
    public void insertCookies(Login logininf) throws SQLException {
        PreparedStatement st;
        String insert = "INSERT INTO login (session_id, user_id) values (?,?);";
        st = conn.prepareStatement(insert);
        st.setInt(2, logininf.user_id());
        st.setObject(1, logininf.cookies());

        st.executeUpdate();
    }

    @Override
    public boolean checkCred(String email, String pwd) throws SQLException {
        Optional<Login> userdate = this.findByUserandPassword(email, pwd);
        return userdate.isPresent();
    }

    @Override
    public Optional<Login> findByUserandPassword(String email, String pwd) throws SQLException {
        PreparedStatement st;
        String select = "SELECT users.id as user_id,email,pwd from users where pwd=? and email=?;";
        st = conn.prepareStatement(select);
        st.setString(2, email);
        st.setString(1, pwd);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            Login login = Login.fromRsWithioutCookies(rs);
            return Optional.of(login);
        }
        return Optional.empty();
    }


}
