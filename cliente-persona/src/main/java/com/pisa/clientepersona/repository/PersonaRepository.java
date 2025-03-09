package com.pisa.clientepersona.repository;

import com.pisa.clientepersona.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}