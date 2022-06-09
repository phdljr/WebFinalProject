package kr.ac.kumoh.backend.repository;

import kr.ac.kumoh.backend.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("select p from Person p join fetch p.movie where p.movie.title = :movieName")
    List<Person> getAllPerson(@Param("movieName") String movieName);
}
