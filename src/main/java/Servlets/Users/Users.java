package Servlets.Users;

import Servlets.DAO;

public interface Users extends DAO<User> {
    void put(User x);

    Iterable<User> get();


}
