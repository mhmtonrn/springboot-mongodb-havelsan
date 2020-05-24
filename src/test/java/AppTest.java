import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import db.GenericDeleteTest;
import db.GenericSaveTest;
import db.GenericUpdateTest;
import db.GenericfindTest;
import rest.RestOpTest;
import rest.RestUpdateTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        GenericfindTest.class,
        GenericSaveTest.class,
        GenericUpdateTest.class,
        GenericDeleteTest.class,
        RestOpTest.class,
        RestUpdateTest.class
})
public class AppTest {

}