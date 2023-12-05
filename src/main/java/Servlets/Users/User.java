package Servlets.Users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public record User(Integer id,String name, String url, String pwd,String created) {
    @Override
    public String toString() {
        return "Name %s \n link %s".formatted(name, url);
    }

    public String toHtml() {
        return
                """
                       
                            <tr>
                             <form action="" method="post">
                              <td >
                                <input name="name" value="%s"  readonly style="all: unset;border:5px dotted teal;background-color:#eafafb;"/></td>
                              </td>
                              <td>
                              <img src="%s" alt="Girl in a jacket" width="100" height="125">
                              </td>
                            </tr>                    
                            <tr>
                              <td> 
                       

                                   <button type="submit" name="des_button" value="false">no</button>
                                   <button type="submit" name="des_button" value="true">Yes</button>
                               
                              </td>
                                </form>
                            </tr>
                      
                        """.formatted(name, url);
    }
    static User fromRs(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String url = rs.getString("url");
        String pwd = rs.getString("pwd");
        String cdate = rs.getString("create_at");

        return new User(id, name, url,pwd,cdate);
    }



}
