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
                            <button>Yes</button>
                          </td>
                          <td>
                            <button>No</button>
                          </td>
                        </tr>
                        """.formatted(name, url);
    }

}
