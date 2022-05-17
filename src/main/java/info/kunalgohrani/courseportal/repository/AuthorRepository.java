package info.kunalgohrani.courseportal.repository;

import info.kunalgohrani.courseportal.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByFirstNameAndLastName(String firstName, String lastName);
}
