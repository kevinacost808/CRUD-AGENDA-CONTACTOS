package com.kevin.agendaContacto.repository;

import com.kevin.agendaContacto.model.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepository extends JpaRepository <Contacto , Long> {
}
