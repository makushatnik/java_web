package utils;

import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by Ageev Evgeny on 26.01.2016.
 */
public class UrlUtil {
    public static String encodeUrlPathSegment(String path, HttpServletRequest req) {
        String enc = req.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            path = UriUtils.encodePathSegment(path, enc);
        } catch (UnsupportedEncodingException e) {
            //log
        }
        return path;
    }
}
