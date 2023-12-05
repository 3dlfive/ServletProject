package Servlets.Messages;

import Servlets.DAO;
import Servlets.Likes.Action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessagesDao implements DAO<Message> {
    private final Connection conn;

    public MessagesDao(Connection conn) {
        this.conn = conn;
    }
    @Override
    public void save(Message message) throws SQLException {

    }

    @Override
    public Optional<Message> find(int id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Message> findAll() throws SQLException {
        return null;
    }
    public List<Message> findAllUsersMessagetoReciver(int senderid,int reciverid) throws SQLException {
        String select = "SELECT messages.body, messages.sender_id, messages.receiver_id, messages.created_at, sender.name as sender_name, sender.url as sender_url, receiver.name as receiver_name, receiver.url as receiver_url FROM public.messages LEFT JOIN users AS sender ON messages.sender_id = sender.id LEFT JOIN users AS receiver ON messages.receiver_id = receiver.id where messages.receiver_id = ? and messages.sender_id = ?;";
        PreparedStatement st = conn.prepareStatement(select);
        st.setInt(1, senderid);
        st.setInt(2, reciverid);
        ResultSet rs = st.executeQuery();
        ArrayList<Message> data = new ArrayList<>();
        while (rs.next()) data.add(Message.fromRs(rs));
        return data;


    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public void update(Message message) throws SQLException {

    }

}
