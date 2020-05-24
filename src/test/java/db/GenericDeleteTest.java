package db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import com.mongodb.client.result.DeleteResult;

import tr.com.havelsan.demo.Application;
import tr.com.havelsan.demo.model.Books;
import tr.com.havelsan.demo.model.Users;
import tr.com.havelsan.demo.op.DbOperations;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class GenericDeleteTest {
	@Autowired
	private DbOperations dbOperations;

	@Before
	public void setUp() throws Exception {

		dbOperations.removeCollection(Users.class);
		dbOperations.removeCollection(Books.class);

		Books b1 = new Books(123, "Başlangıç", "dan brown");
		Users user2 = new Users("Bob", 38);
		Users user1 = new Users("Alice", 40);
		// save product, verify has ID value after save
		assertNull(b1.getId());
		assertNull(user2.getId());// null before save
		this.dbOperations.save(b1);
		this.dbOperations.save(user2);
		this.dbOperations.save(user1);
		assertNotNull(b1.getId());
		assertNotNull(user2.getId());
		assertNotNull(user1.getId());

	}

	@Test
	public void fetchData() {

		Query query = new Query(Criteria.where("name").is("Bob"));
		Users userA = (Users) dbOperations.findOne(query, Users.class);
		assertEquals(38, userA.getAge());

		Query query2 = new Query();
		Iterable<Users> users = dbOperations.find(query2, Users.class);
		int count = 0;
		for (Users p : users) {
			count++;
		}
		assertEquals(count, 2);

		Iterable<Books> books = dbOperations.find(query2, Books.class);
		int count1 = 0;
		for (Books p : books) {
			count1++;
		}
		assertEquals(count1, 1);
	}

	@Test
	public void delData() {
		Query query = new Query(Criteria.where("name").is("Bob"));
		Users userA = (Users) dbOperations.findOne(query, Users.class);
		DeleteResult result = dbOperations.remove(userA);

		assertEquals(result.getDeletedCount(), 1);
	}

	@After
	public void tearDown() throws Exception {
		// this.customMongoRepository.deleteAll("User");
	}
}