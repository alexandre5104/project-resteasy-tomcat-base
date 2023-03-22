package com.alexandre.services;

import java.util.List;
import com.alexandre.helps.Transactions;
import com.alexandre.model.Book;
import com.alexandre.repositories.BookRepository;

import jakarta.inject.Inject;

public class BookService {
	
	@Inject
	private BookRepository bookRepository;

	@Transactions
	public void salvar(Book book){
		bookRepository.insert(book);
	}

	public List<Book> getBooks() {
		return bookRepository.getBooks();
	}
	
	@Transactions
	public Book update(Book book) {
		return bookRepository.update(book);
	}
	
	public Book getById(int id) {
		return bookRepository.getById(id);
	}
	
	@Transactions
	public Book deletar(int id) {
		return bookRepository.deletar(id);
	}
}
