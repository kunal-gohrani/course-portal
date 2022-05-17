package info.kunalgohrani.courseportal.service;

import info.kunalgohrani.courseportal.model.Author;

import java.util.List;

public interface AuthorService {
    Author getAuthorById(Long id);
    List<Author> getAllAuthors();
    Long saveAuthor(Author author);

    Long updateAuthor(Author author);

    Long deleteAuthor(Long id);

}
