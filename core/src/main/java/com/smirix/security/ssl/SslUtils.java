package com.smirix.security.ssl;

import com.smirix.security.certs.SimpleTrustAllCertsManager;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-05-05
 */
public class SslUtils {

    public static SSLContext getContext() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[] {new SimpleTrustAllCertsManager()};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());

            return sc;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
