package hackathon.baggage;

import java.util.Objects;

/**
 * Created by gormelof on 18.02.2018.
 */

public class Utils {
    public static String emptyToNull(String str) {
        if (Objects.equals(str, "")) {
            return null;
        }

        return str;
    }


}
