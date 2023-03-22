package com.alexandre;

import java.util.HashSet;
import java.util.Set;

import org.jboss.resteasy.plugins.providers.jackson.ResteasyJackson2Provider;

import com.alexandre.excessoes.ConstraintViolationExceptionMapper;
import com.alexandre.excessoes.RepositoryMapper;
import com.alexandre.resources.BookResource;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("")
public class ApplicationConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(BookResource.class);
		s.add(ResteasyJackson2Provider.class);
		s.add(ConstraintViolationExceptionMapper.class);
		s.add(RepositoryMapper.class);
		System.out.println("Iniciou o serviço");
		return s;
	}
	
}
