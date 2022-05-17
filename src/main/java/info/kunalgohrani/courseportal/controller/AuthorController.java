package info.kunalgohrani.courseportal.controller;


import info.kunalgohrani.courseportal.model.Author;
import info.kunalgohrani.courseportal.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/{id}")
    public Author getAuthorByIdAPI(@PathVariable String id){
        return authorService.getAuthorById(Long.valueOf(id));
    }

    @GetMapping("/all")
    public List<Author> getAllAuthorAPI(){
        return authorService.getAllAuthors();
    }


    @PostMapping("/new")
    public ResponseEntity<Object> addAuthorAPI(@RequestBody Author author){
        Map<String,String> response = new HashMap<String,String>();
        Long savedAuthorId = authorService.saveAuthor(author);
        if(!(savedAuthorId==null)){
            response.put("id",String.valueOf(savedAuthorId));
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }else{
            response.put("id",null);
            return new ResponseEntity<>(response,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateAuthorAPI(@RequestBody Author author){
        Map<String,String> response = new HashMap<String,String>();
        Long savedAuthorId = authorService.updateAuthor(author);
        if(!(savedAuthorId==null)){
            response.put("id",String.valueOf(savedAuthorId));
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }else{
            response.put("id",null);
            return new ResponseEntity<>(response,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteAuthorAPI(@PathVariable String id){
        Map<String,String> response = new HashMap<String,String>();
        Long deletedAuthorId = authorService.deleteAuthor(Long.parseLong(id));
        if(!(deletedAuthorId==null)){
            response.put("id",String.valueOf(deletedAuthorId));
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else{

            response.put("id",null);
            return new ResponseEntity<>(response,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
