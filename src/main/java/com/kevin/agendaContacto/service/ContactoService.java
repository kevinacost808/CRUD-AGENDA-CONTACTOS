package com.kevin.agendaContacto.service;

import com.kevin.agendaContacto.model.Contacto;

import java.util.List;

public interface ContactoService {
    List<Contacto> obtenerContactos();
    Contacto obtenerContactoId(Long id);
    Contacto agregarContacto(Contacto contacto);
    Contacto editarContacto(Contacto contacto, Long id);
    void eliminarContacto(Long id);
}
