package com.smirix.rest.builders;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Виктор on 06.10.2018.
 */
public class ResponseWrapperFilter implements WriterInterceptor{

    @Override
    public void aroundWriteTo(WriterInterceptorContext interceptorContext)
            throws IOException, WebApplicationException {
        System.out.println("ServerWriterInterceptor1 invoked");
        OutputStream outputStream = interceptorContext.getOutputStream();
        String responseContent = "\nResponse changed in ServerWriterInterceptor1.";
        outputStream.write(responseContent.getBytes());
        interceptorContext.setOutputStream(outputStream);

        interceptorContext.proceed();
    }
}
