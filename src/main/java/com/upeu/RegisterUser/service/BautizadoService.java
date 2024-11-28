package com.upeu.RegisterUser.service;

import com.upeu.RegisterUser.interfaceService.IbautizadoService;
import com.upeu.RegisterUser.interfaces.IBautizado;
import com.upeu.RegisterUser.modelo.Bautizado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BautizadoService implements IbautizadoService {

    @Autowired
    private IBautizado data;

    @Override
    public List<Bautizado> listar() {
        return (List<Bautizado>) data.findAll();
    }

    @Override
    public Optional<Bautizado> listarId(int id) {
        return data.findById(id);
    }

    @Override
    public int save(Bautizado b) {
        Bautizado bautizadoGuardado = data.save(b);  // Guarda el Bautizado
        return bautizadoGuardado != null ? 1 : 0; // Devuelve 1 si se guardó correctamente
    }

    @Override
    public void deleted(int id) {
        data.deleteById(id); // Elimina el Bautizado por su ID
    }
}
