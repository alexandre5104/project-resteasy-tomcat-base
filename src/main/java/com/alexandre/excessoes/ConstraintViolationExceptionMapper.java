package com.alexandre.excessoes;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.alexandre.model.ErrorBeanValidation;
import com.google.common.collect.Iterables;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException>{
       
	@Override
	public Response toResponse(ConstraintViolationException exception) {
		Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        List<String> errorMessages = constraintViolations.stream()
                .map(constraintViolation -> {
                    String name = Iterables.getLast(constraintViolation.getPropertyPath()).getName();
                    return name + " " + constraintViolation.getMessage();
                })
                .collect(Collectors.toList());
        
        return Response
                .status(Response.Status.BAD_REQUEST)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(errorMessages)
                .build();
	}
	
}