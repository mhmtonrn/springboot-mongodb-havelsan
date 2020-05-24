package tr.com.havelsan.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tr.com.havelsan.demo.model.Books;
import tr.com.havelsan.demo.model.Users;

@RestController
@RequestMapping("/api")
public class DemoApi {

    @Autowired
    private ApiService apiService;

    @PostMapping("/save/book")
    public Books saveBook(@RequestBody Books entity){
        return apiService.save(entity);
    }
    
    @PostMapping("/save/user")
    public Users saveBook(@RequestBody Users entity){
        return apiService.save(entity);
    }
    
    @GetMapping("/get/user/byName/{name}")
    public ResponseEntity<List<Users>> getUserByName(@PathVariable String name) {
    	List<Users> user = apiService.getUserByName(name);
    	System.out.println(user);
		return new ResponseEntity<>(user,HttpStatus.OK);   	
    }
    
    @GetMapping("/get/user/byId/{id}")
    public List<Users> getUserById(@PathVariable String id) {
		return apiService.getUserById(id);    	
    }
    
    @GetMapping("/get/book/byName/{name}")
    public List<Books> getBookByName(@PathVariable String name) {
    	return apiService.getBookByName(name); 	
    }
    
    @GetMapping("/get/book/byId/{id}")
    public List<Books> getBookById(@PathVariable String id) {
		return apiService.getBookById(id);    	
    }
    
    @PutMapping("/put/book")
    public Books updateBook(@RequestBody Books book) {
    	return apiService.updateBook(book);
    }

    @DeleteMapping("/del/book")
    public ResponseEntity<Boolean> deleteBook(@RequestBody Books book) {
    	return new ResponseEntity<>(apiService.deleteBook(book),HttpStatus.OK);
    }
    
}
