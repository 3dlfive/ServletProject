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

    @Override
    public String name() {
        return name;
    }
}
