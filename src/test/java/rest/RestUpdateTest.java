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
public class RestUpdateTest extends AbstractTest {

	private Books book;
	@Autowired
	private DbOperations dbOperations;

	@Before
	public void setUp() throws Exception {
		super.setUp();

		dbOperations.removeCollection(Users.class);
		dbOperations.removeCollection(Books.class);

		String uri = "/api/save/book";
		Books book = new Books();
		book.setName("test");
		book.setAuthorName("test");
		book.setIsbn(322);

		String inputJson = super.mapToJson(book);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		Books response = super.mapFromJson(mvcResult.getResponse().getContentAsString(), Books.class);
		this.book = response;
		assertNotNull(response.getId());
	}

	@Test
	public void updateBook() throws Exception {
		String uri = "/api/put/book";
		this.book.setIsbn(4242);

		String inputJson = super.mapToJson(book);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		Books response = super.mapFromJson(mvcResult.getResponse().getContentAsString(), Books.class);
		assertEquals(4242, response.getIsbn().intValue());

	}

	@After
	public void tearDown() throws Exception {
	}
}