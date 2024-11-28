package com.upeu.RegisterUser.controlador;

import com.upeu.RegisterUser.interfaceService.IbautizadoService;
import com.upeu.RegisterUser.interfaceService.IpersonasService;

import com.upeu.RegisterUser.modelo.Persona;
import com.upeu.RegisterUser.modelo.Bautizado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class Controlador {

    @Autowired
    private IpersonasService personasService;

    @Autowired
    private IbautizadoService bautizadoService;

    // Página principal
    @GetMapping("/")
    public String home() {
        return "home"; // Carga la página principal (home.html)
    }


    // Listar personas
    @GetMapping("/listar")
    public String listarPersonas(Model model) {
        List<Persona> personas = personasService.listar();
        model.addAttribute("personas", personas);
        return "index";
    }



    // Mostrar formulario para agregar una nueva persona
    @GetMapping("/new")
    public String agregarPersona(Model model) {
        model.addAttribute("persona", new Persona());
        return "form"; // Debe coincidir con el nombre del archivo en templates (form.html)
    }

    // Guardar una nueva persona
    @PostMapping("/save")
    public String savePersona(@Validated Persona p, Model model) {
        if (p.getNombre() == null || p.getApellido() == null || p.getEdad() == 0) {
            model.addAttribute("error", "Debe llenar todos los campos correctamente.");
            return "form";
        }
        personasService.save(p);
        return "redirect:/listar";
    }



    // Editar una persona
    @GetMapping("/editar/{id}")
    public String editarPersona(@PathVariable int id, Model model) {
        Optional<Persona> persona = personasService.listarId(id);
        if (persona.isPresent()) {
            model.addAttribute("persona", persona.get());
            return "form";
        } else {
            return "redirect:/listar";
        }
    }


    // Eliminar una persona
    @GetMapping("/eliminar/{id}")
    public String eliminarPersona(@PathVariable int id) {
        personasService.deleted(id);
        return "redirect:/listar";
    }
}
