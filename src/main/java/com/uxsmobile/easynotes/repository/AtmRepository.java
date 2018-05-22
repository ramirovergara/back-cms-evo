package com.uxsmobile.easynotes.repository;

import com.uxsmobile.easynotes.model.Atm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtmRepository extends JpaRepository<Atm, Long> {

}