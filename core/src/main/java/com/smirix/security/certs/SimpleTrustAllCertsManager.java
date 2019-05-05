package com.smirix.security.certs;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-05-05
 */
public class SimpleTrustAllCertsManager implements X509TrustManager {

    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        //do nothing
    }

    @Override
    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        //do nothing
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
