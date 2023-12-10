package com.kevin.agendaContacto.controller;

import com.kevin.agendaContacto.model.Contacto;
import com.kevin.agendaContacto.service.ContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/nuevo")
    public String abrirFormulario(Model model){
        model.addAttribute("contacto",new Contacto());
        return "contactoFrm";
    }

    @PostMapping("/nuevo")
    public String agregarContacto(@Validated Contacto contacto, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("contacto", contacto);
            return "contactoFrm";
        }

        contactoService.agregarContacto(contacto);
        redirectAttributes.addFlashAttribute("msgExito","El contacto se guardó con éxito");
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String abrirFormularioEditar(@PathVariable Long id, Model model){
        model.addAttribute("contacto", contactoService.obtenerContactoId(id));
        return "contactoFrm";
    }

    @PostMapping("/editar/{id}")
    public String actualizarContacto(@PathVariable Long id, @Validated Contacto contacto, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("contacto", contacto);
            return "contactoFrm";
        }

        contactoService.editarContacto(contacto,id);

        redirectAttributes.addFlashAttribute("msgExito","El contacto se actualizó correctamente");
        return "redirect:/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarContacto(@PathVariable Long id, RedirectAttributes redirectAttributes){
        contactoService.eliminarContacto(id);
        redirectAttributes.addFlashAttribute("msgExito",
                "El contacto se eliminó correctamente");
        return "redirect:/";
    }
}
