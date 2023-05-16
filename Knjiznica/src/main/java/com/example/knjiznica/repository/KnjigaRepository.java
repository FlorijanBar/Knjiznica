package com.example.knjiznica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.knjiznica.model.Knjiga;

@Repository
public interface KnjigaRepository extends JpaRepository<Knjiga, Long> {
	
	 @Query(value="SELECT k FROM Knjiga k WHERE k.studij = :studij",nativeQuery = true)
	 Iterable<Knjiga> findStudij(@Param("studij") String studij);


}
