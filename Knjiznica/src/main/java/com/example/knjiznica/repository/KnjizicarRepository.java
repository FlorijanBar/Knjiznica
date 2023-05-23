package com.example.knjiznica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.knjiznica.model.Knjizicar;

@Repository
public interface KnjizicarRepository extends JpaRepository<Knjizicar, Long> {

	
	
	    

}
