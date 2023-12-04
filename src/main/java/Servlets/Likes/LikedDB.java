package Servlets.Likes;

import Servlets.Users.User;

import java.util.ArrayList;

public class LikedDB implements Liked{


    private final ArrayList<User> data = new ArrayList<User>();

    @Override
    public void put(User x) {
        data.add(x);
    }

    @Override
    public Iterable<User> get() {
        return data;
    }

    @Override
    public String toString() {
        return "SelectedDB{" +
                "data=" + data +
                '}';
    }
}
