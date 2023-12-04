package Servlets.Users;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoUsersList implements Users{
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

    public int size(){
        return data.size();
    }

    @Override
    public void save(User user) throws SQLException {
        data.add(user);
    }

    @Override
    public Optional<User> find(int id) throws SQLException {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<User> findAll() throws SQLException {
        return data;
    }

    @Override
    public void delete(int id) throws SQLException {
    data.remove(id);
    }

    @Override
    public void update(User user) throws SQLException {

    }
}
