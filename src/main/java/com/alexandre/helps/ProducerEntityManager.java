package com.alexandre.helps;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class ProducerEntityManager {

	private EntityManagerFactory factory;

	public ProducerEntityManager() {
		factory = Persistence.createEntityManagerFactory("resteasyPU");
	}

	@Produces @RequestScoped
	public EntityManager createEntityManager() {
		System.out.println("Criou uma instancia do EntityManager");
		return factory.createEntityManager();
	}

	public void closeEntityManager(@Disposes EntityManager manager) {
		manager.close();
	}

}
