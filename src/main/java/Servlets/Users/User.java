package Servlets.Users;

public record User(String name,String url) {
    @Override
    public String toString() {
        return "Name %s \n link %s".formatted(name, url);
    }

    public String toHtml() {
        return
                """
                        <tr>
                          <td>%s
                          </td>
                          <td>
                          <img src="%s" alt="Girl in a jacket" width="100" height="125">
                          </td>
                        </tr>                    
                        <tr>
                          <td> 
                        
                          </td>
                          <td>
                           <form action="" method="post">
                    
                               <button type="submit" name="des_button" value="false">no</button>
                               <button type="submit" name="des_button" value="true">Yes</button>
                           </form>
                          </td>
                        </tr>
                        """.formatted(name, url);
    }

}
