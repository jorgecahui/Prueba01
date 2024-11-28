/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.upeu.RegisterUser.interfaces;

import com.upeu.RegisterUser.modelo.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author Home
 */
@Repository
public interface IPersona extends CrudRepository<Persona, Integer>{

}
