package org.jboss.bookstore.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
//import javax.enterprise.inject.Model;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.bookstore.ejb.BookEJB;
import org.jboss.bookstore.model.Book;

//@Model
@Named
@RequestScoped
public class BookController {
	@Inject
	private BookEJB bookEJB;
	private Book book = new Book();
	private List<Book> bookList = new ArrayList<>();
	
	public BookController() {}
	
	public BookEJB getBookEJB() {
		return bookEJB;
	}


	public void setBookEJB(BookEJB bookEJB) {
		this.bookEJB = bookEJB;
	}


	public Book getBook() {
		return book;
	}


	public void setBook(Book book) {
		this.book = book;
	}

	public String doCreateBook() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		if (book.getIsbn() == null || book.getIsbn().equals("")) {
			ctx.addMessage("bookForm:isbn", new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Неверный ISBN", "Вы должны ввести ISBN"));
		}
		if (book.getTitle() == null || book.getTitle().equals("")) {
			ctx.addMessage("bookForm:isbn", new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Неверное название", "Вы должны ввести название книги"));
		}
		if (ctx.getMessageList().size() != 0) return null;
		
		try {
			bookEJB.createBook(book);
			//null - global message
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Книга создана",
					"Книга " + book.getTitle() + " была создана с id - " + book.getId()));
		} catch (Exception e) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Книга не может быть создана", e.getMessage()));
		}
		bookList = bookEJB.findBooks();
		//return "success";
		return "newBook.xhtml";
		//return null;
	}
	
	public void doFindBookById(Long id) {
		book = bookEJB.findBookById(id);
	}
}
