package br.com.medvia.util;

import java.nio.charset.Charset;
import org.springframework.http.MediaType;

/**
 *
 * @author Willian Kirschner willkev@gmail.com
 * Apenas uma linha a mais para realizar um teste do github, obrigada!
 */
public class TestUtil {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );
}
