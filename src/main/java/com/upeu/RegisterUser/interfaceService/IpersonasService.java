/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.upeu.RegisterUser.interfaceService;

import com.upeu.RegisterUser.modelo.Persona;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Home
 */
public interface IpersonasService {
    public List<Persona>listar();
    public Optional<Persona>listarId(int id);
    public int save(Persona p);
    public void deleted(int id);



}
