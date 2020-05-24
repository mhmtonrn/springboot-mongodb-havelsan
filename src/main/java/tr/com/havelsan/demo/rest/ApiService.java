package tr.com.havelsan.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.DeleteResult;

import tr.com.havelsan.demo.model.Books;
import tr.com.havelsan.demo.model.Users;
import tr.com.havelsan.demo.op.DbOperations;
import tr.com.havelsan.demo.repository.User;

@Service
public class ApiService {

	@Autowired
	private DbOperations dbOperations;

	@Autowired
	private User user;

	public <T> T save(T t) {
		user.setName("currentUser");
		return dbOperations.save(t);
	}

	public List<Users> getUserByName(String name) {
		user.setName("currentUser");
		Query query = new Query(Criteria.where("name").is(name));
		return dbOperations.find(query, Users.class);
	}
	
	public List<Users> getUserById(String id) {
		user.setName("currentUser");
		Query query = new Query(Criteria.where("id").is(id));
		return dbOperations.find(query, Users.class);
	}
	
	public List<Books> getBookByName(String name) {
		user.setName("currentUser");
		Query query = new Query(Criteria.where("name").is(name));
		return dbOperations.find(query, Books.class);
	}
	
	public List<Books> getBookById(String id) {
		user.setName("currentUser");
		Query query = new Query(Criteria.where("id").is(id));
		return dbOperations.find(query, Books.class);
	}

	public Books updateBook(Books book) {
		user.setName("currentUser");
		return dbOperations.update(book);
	}

	public boolean deleteBook(Books book) {
		user.setName("currentUser");
		DeleteResult resultSize = dbOperations.remove(book);
		return resultSize.getDeletedCount()>0 ? true: false;
	}

}
