package Servlets.Users;

import java.util.ArrayList;

public class UsersList implements Users{
    private final ArrayList<User> data = new ArrayList<User>();

    @Override
    public void put(User x) {
        data.add(x);
    }

    @Override
    public Iterable<User> get() {
        return data;
    }
}
