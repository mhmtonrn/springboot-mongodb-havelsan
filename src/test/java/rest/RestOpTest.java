package rest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import tr.com.havelsan.demo.Application;
import tr.com.havelsan.demo.model.Books;
import tr.com.havelsan.demo.model.Users;
import tr.com.havelsan.demo.op.DbOperations;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RestOpTest extends AbstractTest {
	
	@Autowired
	private DbOperations dbOperations;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		dbOperations.removeCollection(Users.class);
		dbOperations.removeCollection(Books.class);
	}

	@Test
	public void saveAndGetBook() throws Exception {
		String uri = "/api/save/book";
		Books book = new Books();
		book.setName("suç ve ceza");
		book.setAuthorName("Fyodor Dostoyevski");
		book.setIsbn(123);

		String inputJson = super.mapToJson(book);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		Books response = super.mapFromJson(mvcResult.getResponse().getContentAsString(), Books.class);
		assertNotNull(response.getId());
	}
	
	@Test
	public void saveAndGetUser() throws Exception {
		String uri = "/api/save/user";
		Users book = new Users();
		book.setName("mehmet");
		book.setAge(27);

		String inputJson = super.mapToJson(book);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		Users response = super.mapFromJson(mvcResult.getResponse().getContentAsString(), Users.class);
		assertNotNull(response.getId());
	}
	


	@After
	public void tearDown() throws Exception {
	}
}