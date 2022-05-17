package info.kunalgohrani.courseportal.service;

import info.kunalgohrani.courseportal.exception.AuthorNotFoundException;
import info.kunalgohrani.courseportal.model.Author;
import info.kunalgohrani.courseportal.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public Author getAuthorById(Long id) {
        log.info("----In AuthorServiceImpl.getAuthorById----");
        Author author = authorRepository.findById(id).orElse(null);
        if(author==null){
            throw new AuthorNotFoundException("Author not found");
        }
        log.info("----Out of AuthorServiceImpl.getAuthorById----");
        return author;
    }

    @Override
    public List<Author> getAllAuthors() {
        log.info("----In AuthorServiceImpl.getAllAuthors----");
        log.info("----In AuthorServiceImpl.getAllAuthors----");
        return authorRepository.findAll();
    }

    @Override
    public Long saveAuthor(Author author) {
        log.info("----In AuthorServiceImpl.saveAuthor----");
        Author savedAuthor;
        try{
            savedAuthor = authorRepository.save(author);
        }catch(Exception ex){
            log.info("----Exception in AuthorServiceImpl.saveAuthor----");
            log.error(Arrays.toString(ex.getStackTrace()));
            return null;
        }
        log.info("----Out of AuthorServiceImpl.saveAuthor----");
        return savedAuthor.getId();
    }

    @Override
    public Long updateAuthor(Author author) {
        log.info("----In AuthorServiceImpl.updateAuthor----");
        Author authordb = getAuthorById(author.getId());
        Author updatedAuthor;
        try{
            updatedAuthor = authorRepository.save(author);
        }catch(Exception ex){
            log.info("----Exception in AuthorServiceImpl.updateAuthor----");
            log.error(Arrays.toString(ex.getStackTrace()));
            return null;
        }
        log.info("----Out of AuthorServiceImpl.updateAuthor----");
        return updatedAuthor.getId();
    }

    @Override
    public Long deleteAuthor(Long id) {
        log.info("----In AuthorServiceImpl.deleteAuthor----");
        Author authordb = getAuthorById(id);
        try{
            authorRepository.delete(authordb);
        }catch(Exception ex){
            log.info("----Exception in AuthorServiceImpl.deleteAuthor----");
            log.error(Arrays.toString(ex.getStackTrace()));
            return null;
        }
        log.info("----Out of AuthorServiceImpl.deleteAuthor----");
        return id;
    }
}
