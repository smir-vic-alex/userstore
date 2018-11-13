package com.smirix.rest.filters;

import com.smirix.rest.exception.ExceptionMapperBase;
import com.smirix.rest.exception.RestException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by Виктор on 06.10.2018.
 */
@Provider
public class ExceptionMapperImpl extends ExceptionMapperBase {

    @Override
    public Response toResponse(RestException exception) {
        return super.toResponse(exception);
    }
}
