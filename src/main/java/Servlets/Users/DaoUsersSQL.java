package Servlets.Users;

import Helpers.Database;
import Servlets.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoUsersSQL implements DAO<User> {
    private final Connection  conn;

    public DaoUsersSQL(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void save(User user) throws SQLException {

    }

    @Override
    public Optional<User> find(int id) throws SQLException {
        String select = "SELECT id, name, url, pwd,create_at FROM users WHERE ID = ?";
        PreparedStatement st = conn.prepareStatement(select);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            User user = User.fromRs(rs);
            return Optional.of(user);
        }
        return Optional.empty();

    }

    @Override
    public List<User> findAll() throws SQLException {
        String select = "select id, name, url, pwd,create_at from users";
        PreparedStatement st = conn.prepareStatement(select);
        ResultSet rs = st.executeQuery();
        ArrayList<User> data = new ArrayList<>();
        while (rs.next()) data.add(User.fromRs(rs));
        return data;

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public void update(User user) throws SQLException {

    }
    public int size() throws SQLException {
        String select = "SELECT COUNT(id) FROM users;";
        PreparedStatement st = conn.prepareStatement(select);
        ResultSet rs = st.executeQuery();
        if (rs.next()){
            return rs.getInt("count");
        } else {
            return 0;
        }

    }
    public Optional<Integer> reciveSenderId(String uuid) throws SQLException {
        String select = "select user_id from login where session_id = ?::uuid;";
        PreparedStatement st = conn.prepareStatement(select);
        st.setString(1, uuid);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return Optional.of(rs.getInt("user_id"));
        }else {
            return Optional.empty();
        }
    }
}
