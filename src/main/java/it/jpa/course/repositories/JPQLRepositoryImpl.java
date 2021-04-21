package it.jpa.course.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import it.jpa.course.beans.BooksCopyDTO;
import it.jpa.course.beans.BooksDTO;
import it.jpa.course.entities.AuthorV4;
import it.jpa.course.entities.BookV2;
import it.jpa.course.entities.BookV4;

@Component
public class JPQLRepositoryImpl implements JPQLRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<BookV2> getSoldBooks() {
		TypedQuery<BookV2> query = 
				em.createQuery("select b from BookV2 b join fetch b.orderV2 where b.orderV2 IS NOT NULL", BookV2.class);
		//return query.setHint("org.hibernate.cacheable",true).getResultList();
		return query.getResultList();
	}

	@Override
	public List<BookV4> getBooksByAuthor(String author) {
		TypedQuery<AuthorV4> queryAuthor = em
				.createQuery("select a from AuthorV4 a where a.name=:author", AuthorV4.class)
				.setParameter("author", author);
		AuthorV4 authorV4 = queryAuthor.getSingleResult();
		TypedQuery<BookV4> queryBook = em
				.createQuery("select b from BookV4 b where :author MEMBER OF b.authorV4", BookV4.class)
				.setParameter("author", authorV4);
		return queryBook.getResultList();
	}

	@Override
	public Double getRevenue() {
		TypedQuery<Double> queryInvoice = em
				.createQuery("select sum(i.price) from InvoiceV3 i", Double.class);
		return queryInvoice.getSingleResult();
	}

	@Override
	public List<String> getAuthors() {
		TypedQuery<String> queryInvoice = em
				.createQuery("select concat(concat(a.id,','), a.name) from AuthorV4 a", String.class);
		return queryInvoice.getResultList();
	}
	
	@Override
	public BooksCopyDTO getBooksCopy(String bookName) {
		TypedQuery<BooksCopyDTO> queryBooksCopy = em
				.createQuery("select new it.jpa.course.beans.BooksCopyDTO(b.name, count(b.name)) from BookV2 b where b.name=:bookName and b.orderV2 IS NULL group by(b.name)", BooksCopyDTO.class)
				.setParameter("bookName", bookName);
		return queryBooksCopy.getSingleResult();
	}
	
	@Override
	public List<BooksDTO> getBooks(int pageStart, int pageSize) {
		TypedQuery<BooksDTO> query = em.createQuery("select new it.jpa.course.beans.BooksDTO(b.name, b.id) from BookV2 b", BooksDTO.class)
				.setFirstResult(pageStart)
				.setMaxResults(pageSize);
		return query.getResultList();
	}

}
