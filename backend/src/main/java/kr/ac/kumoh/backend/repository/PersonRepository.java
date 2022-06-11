package kr.ac.kumoh.backend.repository;

import kr.ac.kumoh.backend.domain.Movie;
import kr.ac.kumoh.backend.domain.Person;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("select p from Person p join fetch p.movie where p.movie.title = :movieName")
    List<Person> getAllPerson(@Param("movieName") String movieName);

    @EntityGraph(value = "person.movie", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select p from Person p where p.name like CONCAT('%',:personName,'%')")
    List<Person> searchPersonByName(@Param("personName") String personName);
}
