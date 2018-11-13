package com.smirix.rest.exception;

import org.springframework.http.MediaType;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Created by Виктор on 28.10.2018.
 */
public class ExceptionMapperBase implements ExceptionMapper<RestException> {

    @Override
    public Response toResponse(RestException exception) {
        return Response.status(200)
                .entity(exception.getMessage())
                .type(MediaType.APPLICATION_JSON.getType())
                .build();
    }
}
