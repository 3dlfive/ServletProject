package Servlets.SelectedUsers;

import Servlets.Users.User;

public interface Selected {

    void put(User x);

    Iterable<User> get();

}
