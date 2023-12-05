package Servlets.Likes;

import Servlets.Users.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public record Action(Integer user_id, Integer profile_id, String action, String profileName, String date,String url) {
    public Action(Integer user_id, Integer profile_id, String action) {
        this(user_id, profile_id, action,"null","t1","url");
    }

    public static Action fromRs(ResultSet rs) throws SQLException {
        int user_id = rs.getInt("user_id");
        int profile_id = rs.getInt("profile_id");
        String action = rs.getString("action");
        String profileName = rs.getString("profile_name");
        String date = rs.getString("op_at");
        String url = rs.getString("url");

        return new Action(user_id, profile_id,action,profileName,date,url);
    }
}
