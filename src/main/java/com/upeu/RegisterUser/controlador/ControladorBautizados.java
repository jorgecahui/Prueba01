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
import java.time.format.DateTimeFormatter;


import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class ControladorBautizados {

    @Autowired
    private IpersonasService personasService;

    @Autowired
    private IbautizadoService bautizadoService;

    // Página principal


    @GetMapping("/bautizados/new")
    public String nuevoBautizo(Model model) {
        List<Persona> personas = personasService.listar(); // Obtener lista de personas
        model.addAttribute("personas", personas); // Pasar las personas al modelo
        model.addAttribute("bautizado", new Bautizado()); // Crear un objeto vacío de Bautizado
        return "formBau"; // Redirigir a la vista formBau.html
    }



    // Listar personas bautizadas
    @GetMapping("/listarBautizados")
    public String listarBautizados(Model model) {
        List<Bautizado> bautizados = bautizadoService.listar();
        model.addAttribute("bautizados", bautizados);
        return "indexBau"; // Debe corresponder con el nombre del archivo en templates (bautizados.html)
    }





    // Mostrar formulario para registrar un bautizo
    @GetMapping("/bautizar/{id}")
    public String bautizarPersona(@PathVariable int id, Model model) {
        Optional<Persona> persona = personasService.listarId(id);
        if (persona.isPresent()) {
            model.addAttribute("persona", persona.get());
            model.addAttribute("bautizado", new Bautizado());
            return "bautizar"; // Debe coincidir con el nombre del archivo en templates (bautizar.html)
        } else {
            return "redirect:/listar";
        }
    }

    // Guardar un bautizo

    @PostMapping("/guardarBautizo")
    public String saveBautizados(@Validated Bautizado b, Model model) {
        // Verifica si la persona está correctamente asignada
        if (b.getPersona() == null) {
            model.addAttribute("error", "Debe seleccionar una persona.");
            return "formBau"; // Redirigir al formulario si no hay persona seleccionada
        }

        // Verifica si los campos de fecha o lugar están vacíos
        if (b.getFechaBautizo() == null) {
            model.addAttribute("error", "Debe ingresar la fecha del bautizo.");
            return "formBau";
        }

        bautizadoService.save(b); // Guarda el bautizo
        return "redirect:/listarBautizados"; // Redirige a la lista de bautizados
    }



    @GetMapping("/bautizados/editar/{id}")
    public String editarBautizados(@PathVariable int id, Model model) {
        Optional<Bautizado> bautizado = bautizadoService.listarId(id);

        if (bautizado.isPresent()) {
            model.addAttribute("bautizado", bautizado.get());
            // Cargar la lista de personas para el combo box
            model.addAttribute("personas", personasService.listar()); // Asegúrate de tener un servicio para listar personas
            return "formBau";
        } else {
            return "redirect:/listarBautizados";
        }

    }

    @GetMapping("/bautizados/eliminar/{id}")
    public String eliminarPersona(@PathVariable int id) {
        bautizadoService.deleted(id);
        return "redirect:/listarBautizados";
    }
}