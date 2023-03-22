package com.alexandre.excessoes;

import com.alexandre.model.ErrorMessage;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class RepositoryMapper implements ExceptionMapper<RepositoryException>{

	@Override
	public Response toResponse(RepositoryException exception) {
		
		ErrorMessage error = new ErrorMessage(exception.getMessage(), exception.getCode());
		if (exception.getCode() == 400) {
			return Response.status(Status.BAD_REQUEST)
					.entity(error)
					.type(MediaType.APPLICATION_JSON)
					.build();
		} else if (exception.getCode() == 404) {
			return Response.status(Status.NOT_FOUND)
					.entity(error)
					.type(MediaType.APPLICATION_JSON)
					.build();
		} else {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(error)
					.type(MediaType.APPLICATION_JSON)
					.build();
		}
	}

}
