package db;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tr.com.havelsan.demo.Application;
import tr.com.havelsan.demo.model.Books;
import tr.com.havelsan.demo.model.Users;
import tr.com.havelsan.demo.op.DbOperations;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class GenericSaveTest {
    @Autowired
    private DbOperations dbOperations;

    @Before
    public void setUp() throws Exception {
        /*this.customMongoRepository.deleteAll(Users.class.getSimpleName());
        this.customMongoRepository.deleteAll(Books.class.getSimpleName());*/

    }
    @Test
    public void saveUserData(){

        Users user1= new Users("Alice", 23);
        Users user2= new Users("Bob", 38);
        //save product, verify has ID value after save
        assertNull(user1.getId());
        assertNull(user2.getId());//null before save
        this.dbOperations.save(user1);
        this.dbOperations.save(user2);
        assertNotNull(user1.getId());
        assertNotNull(user2.getId());

    }

    @Test
    public  void saveBooksData(){
        Books b1= new Books(123, "Başlangıç","dan brown");
        Books b2= new Books(456, "küçük prens","Antoine de Saint-Exupery");
        //save product, verify has ID value after save
        assertNull(b1.getId());
        assertNull(b2.getId());//null before save
        this.dbOperations.save(b1);
        this.dbOperations.save(b2);
        assertNotNull(b1.getId());
        assertNotNull(b2.getId());
    }

    @After
    public void tearDown() throws Exception {
        //this.customMongoRepository.deleteAll("User");
    }
}