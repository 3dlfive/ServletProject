package Servlets.Likes;

import Servlets.Users.User;

public interface Liked {

    void put(User x);

    Iterable<User> get();

}
