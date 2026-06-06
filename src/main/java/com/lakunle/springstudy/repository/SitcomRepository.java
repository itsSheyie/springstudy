package com.lakunle.springstudy.repository;

import com.lakunle.springstudy.model.Sitcom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface SitcomRepository extends JpaRepository<Sitcom, Long> {

    Optional<Sitcom> findByName(String name);
    List<Sitcom> findByReleaseYear(int releaseYear);
    List<Sitcom> findByProducer(String producer);

    boolean existsByName(String name);
    boolean existsByNameAndReleaseYearAndProducer(String name, int releaseYear, String producer);

    Optional<Sitcom> findByNameAndReleaseYearAndProducer(String name, int releaseYear, String producer);


}
