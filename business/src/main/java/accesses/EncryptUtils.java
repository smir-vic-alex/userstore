package accesses;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by Виктор on 24.07.2017.
 */
public class EncryptUtils {

    public static String code(String string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        stringBuilder.append("123_salt_123");

        return stringBuilder.insert(0, DigestUtils.md5(stringBuilder.toString())).toString();
    }
}
