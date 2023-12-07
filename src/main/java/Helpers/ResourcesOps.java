package Helpers;

import java.net.URISyntaxException;
import java.util.Objects;

public class ResourcesOps {

    public static String dirUnsafe(String prefix) {
        try {
            return
                    Objects.requireNonNull(ResourcesOps.class
                                    .getClassLoader()
                                    .getResource(prefix))
                            .toURI()
                            .getPath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            System.out.println("fc");
            throw new RuntimeException(e);
        }
    }

}
