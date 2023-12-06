package Servlets.Login;

import Servlets.DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface LoginDAO<Login> {
    void insertCookies(Login loginf) throws SQLException;
    boolean checkCred(String email,String pwd) throws SQLException;
    Optional<Login> findByUserandPassword(String email,String pwd) throws SQLException;

}
