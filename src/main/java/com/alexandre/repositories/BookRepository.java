package com.alexandre.repositories;

import java.util.List;
import java.util.Optional;

import com.alexandre.excessoes.RepositoryException;
import com.alexandre.model.Book;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@RequestScoped
public class BookRepository {

	@Inject
	private EntityManager manager;

	public void insert(Book book) {
		try {
			manager.persist(book);
		} catch (RuntimeException e) {
			throw new RepositoryException("Erro ao salvar marca no banco de dados: ", 500);
		}
	}

	public List<Book> getBooks(){
		List<Book> clientes = null;
		try {
			clientes = manager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
		} catch (RuntimeException e) {
			throw new RepositoryException("Erro ao recuperar dados do banco ", 500);
		}
		return clientes;
	}

	public Book getById(int id) {
		return Optional
				.of(manager.find(Book.class, id))
				.orElseThrow(() -> new RepositoryException("Livro não encontrado", 404)); 
	}

	public Book update(Book bookParam) {

		Book book = null;
		try {
			book = manager.find(Book.class, bookParam.getId());	
			book.setTitle(bookParam.getTitle());
			book.setAuthor(bookParam.getAuthor());
		} catch (NullPointerException e) {
			throw new RepositoryException("Book de id " + bookParam.getId() + " não existe.", 404);
		} catch (RuntimeException e) {
			throw new RepositoryException("Erro ao buscar book com id no banco de dados: ", 500);
		}
		return book;
	}

	public Book deletar(int id) {
		Book book = null;
		try {
			book = manager.find(Book.class, id);
			manager.remove(book);
		} catch (IllegalArgumentException e) {
			throw new RepositoryException("Book de id " + id + " nao existe ", 404);
		} catch (RuntimeException e) {
			throw new RepositoryException("Erro ao buscar book comr id no banco de dados: ", 500);
		}
		return book;
	}

}
