package com.kevin.agendaContacto.controller;

import com.kevin.agendaContacto.model.Contacto;
import com.kevin.agendaContacto.service.ContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;
import java.security.PrivateKey;
import java.util.List;

@Controller
public class ContactoController {
    @Autowired
    private ContactoService contactoService;

    @GetMapping({"/",""})
    public String verPaginaInicio(Model model){
        List<Contacto> contactos = contactoService.obtenerContactos();
        model.addAttribute("contactos", contactos);
        return "index";
    }

    @GetMapping("/abrirFormulario")
    public String abrirFormulario(Model model){
        model.addAttribute("contacto",new Contacto());
        return "contactoFrm";
    }

    @PostMapping("/agregarContacto")
    public String agregarContacto(@Validated Contacto contacto, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("contacto", contacto);
            return "contactoFrm";
        }

        contactoService.agregarContacto(contacto);
        redirectAttributes.addFlashAttribute("msgExito","El contacto se guardó con éxito");
        return "redirect:/";
    }
}
