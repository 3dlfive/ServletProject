package Servlets.Messages;

import java.sql.ResultSet;
import java.sql.SQLException;

public record Message(int sender_id,String sender_name,String sender_url,int receiver_id,String receiver_name,String receiver_url,String body,String created_at) {
    public Message(int sender_id,int receiver_id,String body) {
        this(sender_id,"sender_name","sender_url", receiver_id,"receiver_name","receiver_url",body,"created_at");
    }
    public static Message fromRs(ResultSet rs) throws SQLException {

        String sender_name = rs.getString("sender_name");
        int sender_id = rs.getInt("sender_id");
        int receiver_id = rs.getInt("reciver_id");
        String receiver_name = rs.getString("reciver_name");
        String receiver_url = rs.getString("receiver_url");
        String sender_url = rs.getString("sender_url");

        String body = rs.getString("body");
        String created_at = rs.getString("created_at");

        return new Message(sender_id,sender_name,sender_url, receiver_id,receiver_name,receiver_url,body,created_at);
    }
}
