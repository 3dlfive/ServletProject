package Servlets.Users;

import java.util.ArrayList;
import java.util.Optional;

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

    public Optional<User> findFirst(String name) {
        return data.stream().filter(el->el.name().equals(name)).findFirst();
    }
    public User getByID(int id){
        return data.get(id);
    }
    public int size(){
        return data.size();
    }
}
