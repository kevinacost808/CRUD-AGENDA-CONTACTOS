package com.kevin.agendaContacto.service;

import com.kevin.agendaContacto.model.Contacto;
import com.kevin.agendaContacto.repository.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactoServiceImpl implements ContactoService{
    @Autowired
    private ContactoRepository contactoRepository;

    @Override
    public List<Contacto> obtenerContactos() {
        return contactoRepository.findAll();
    }

    @Override
    public Contacto obtenerContactoId(Long id) {
        return contactoRepository.findById(id).get();
    }

    @Override
    public Contacto agregarContacto(Contacto contacto) {
        return contactoRepository.save(contacto);
    }

    @Override
    public Contacto editarContacto(Contacto contacto, Long id) {
        Contacto contactoEditar = contactoRepository.findById(id).orElse(null);
        if(contactoEditar!=null){
            contactoEditar.setNombre(contacto.getNombre());
            contactoEditar.setApellido(contacto.getApellido());
            contactoEditar.setEmail(contacto.getEmail());
            contactoEditar.setFechaNacimiento(contacto.getFechaNacimiento());
            contactoEditar.setFechaRegistro(contacto.getFechaRegistro());
            return contactoRepository.save(contactoEditar);
        }
        return null;
    }

    @Override
    public void eliminarContacto(Long id) {
        contactoRepository.deleteById(id);
    }
}
