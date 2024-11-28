package com.upeu.RegisterUser.service;

import com.upeu.RegisterUser.interfaceService.IpersonasService;
import com.upeu.RegisterUser.interfaces.IPersona;
import com.upeu.RegisterUser.modelo.Persona;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class PersonasService implements IpersonasService {

    @Autowired
    private IPersona data;

    @Override
    public List<Persona> listar() {
        return (List<Persona>) data.findAll();
    }

    @Override
    public Optional<Persona> listarId(int id) {
        return data.findById(id); // Busca la persona por su ID y devuelve un Optional
    }

    @Override
    public int save(Persona p) {
        Persona personaGuardada = data.save(p); // Guarda la persona y devuelve la entidad guardada
        return personaGuardada != null ? 1 : 0; // Devuelve 1 si la persona fue guardada correctamente
    }



    @Override
    public void deleted(int id) {
        data.deleteById(id); // Elimina la persona por su ID
    }
}
