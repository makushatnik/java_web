package org.jboss.bookstore.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
    @NamedQuery(name = "findAllBooks", query = "SELECT b FROM Book b ORDER BY b.title"),
    @NamedQuery(name = "findBookH2G2", query = "SELECT b FROM Book b WHERE b.title = 'H2G2'")
})
public class Book implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	private String title;
	private float price;
	@Size(max = 2000)
	private String descr;
	private String isbn;
	private int nbOfPage;
	private boolean illustrs;
	private static final long serialVersionUID = 1L;

	public Book() {}
	
	public Book(String nTitle, float nPrice, String nDescr, String nIsbn, int num, boolean hasIll)
    {
        title = nTitle;
        descr = nDescr;
        price = nPrice;
        isbn  = nIsbn;
        nbOfPage = num;
        illustrs = hasIll;
    }
	
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}   
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}   
	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}   
	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}   
	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}   
	public int getNbOfPage() {
		return this.nbOfPage;
	}
	public void setNbOfPage(int nbOfPage) {
		this.nbOfPage = nbOfPage;
	}   
	public boolean getIllustrs() {
		return this.illustrs;
	}

	public void setIllustrs(boolean illustrs) {
		this.illustrs = illustrs;
	} 
}

