package com.alexandre.resources;

import java.util.List;

import org.jboss.resteasy.links.AddLinks;
import org.jboss.resteasy.links.LinkResource;
import org.jboss.resteasy.links.LinkResources;

import com.alexandre.model.Book;
import com.alexandre.services.BookService;

import jakarta.inject.Inject;

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("/books")
@Consumes({"application/json"})
@Produces({"application/json"})
public class BookResource {

	@Inject
	private BookService bookService;

	@AddLinks
	@LinkResources(
			@LinkResource(rel = "listar", value = Book.class)
			)
	@GET
	public List<Book> getBooks() {
		return bookService.getBooks();
	}

	@AddLinks
	@LinkResource
	@POST
	@Path("book")
	public void addBook(@Valid Book book) {
		bookService.salvar(book);
	}

	@AddLinks
	@LinkResource(value = Book.class, pathParameters = {"${id}"}, rel = "GET")
	@GET
	@Path("book/{id}")
	public Book getBook(@PathParam("id") int id) {
		return bookService.getById(id);
	}

	@AddLinks
	@LinkResource(value = Book.class)
	@PUT
	public Book updateBook(Book book) {
		System.out.println(book.getId());
		return bookService.update(book);
	}

	@AddLinks
	@LinkResource(value = Book.class, pathParameters = {"${id}"})
	@DELETE
	@Path("book/{id}")
	public Book deleteBook(@PathParam("id") int id) {
		return bookService.deletar(id);
	}

}
