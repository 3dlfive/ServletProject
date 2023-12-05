package Servlets.Likes;

import Servlets.DAO;
import Servlets.Users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LikedDBDao implements DAO<Action> {
    private final Connection conn;

    public LikedDBDao(Connection conn) {
        this.conn = conn;
    }
    @Override
    public void save(Action liked) throws SQLException {
        PreparedStatement st;
        String insert = "INSERT INTO actions (user_id, profile_id, action) SELECT ?, ?, ? WHERE NOT EXISTS ( SELECT 1 FROM actions WHERE user_id = ? AND profile_id = ? LIMIT 1 );";
        st = conn.prepareStatement(insert);
        st.setInt(1, liked.user_id());
        st.setInt(2, liked.profile_id());
        st.setString(3, liked.action());
        st.setInt(4, liked.user_id());
        st.setInt(5, liked.profile_id());

        st.executeUpdate();
    }

    @Override
    public Optional<Action> find(int id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Action> findAll() throws SQLException {
        String select = "select user_id,profile_id, action, users.name as profile_name,op_at,users.url from actions join users on actions.profile_id = users.id where ACTION LIKE 'liked'";
        PreparedStatement st = conn.prepareStatement(select);
        ResultSet rs = st.executeQuery();
        ArrayList<Action> data = new ArrayList<>();
        while (rs.next()) data.add(Action.fromRs(rs));
        return data;
    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public void update(Action liked) throws SQLException {

    }
}
