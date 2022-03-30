package com.example.com.trabajointegrador.controller;


import com.example.com.trabajointegrador.entidades.Odontologo;
import com.example.com.trabajointegrador.entidades.Paciente;
import com.example.com.trabajointegrador.entidades.Turno;
import com.example.com.trabajointegrador.repository.OdontologoDaoH2;
import com.example.com.trabajointegrador.repository.TurnosDaoH2;
import com.example.com.trabajointegrador.servicios.OdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    OdontologoService odontologoService = new OdontologoService();

    @GetMapping("/id/{turnoId}")
    public Odontologo getTurno(@PathVariable int odontologoId){
        odontologoService.setOdontologoIDao(new  OdontologoDaoH2());
        Odontologo odontologo = odontologoService.buscarOdontologo(odontologoId);
        return odontologo;
    }

    @GetMapping("/verTodos")
    public List<Odontologo> buscarTodos(){
        odontologoService.setOdontologoIDao(new OdontologoDaoH2());
        return odontologoService.buscarTodosOdontologos();
    }

    @PostMapping("/registrar")
    public Odontologo guardar(@RequestBody Odontologo odontologo){
        odontologoService.setOdontologoIDao(new  OdontologoDaoH2());
        return odontologoService.registrarOdontologo(odontologo);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity eliminar(@PathVariable int id){
        odontologoService.setOdontologoIDao(new  OdontologoDaoH2());
        ResponseEntity response = null;
        if (odontologoService.buscarOdontologo(id) == null){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            odontologoService.eliminarOdontolgo(id);
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return response;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Odontologo> actualizar(@RequestBody Odontologo odontologo){
        odontologoService.setOdontologoIDao(new  OdontologoDaoH2());
        ResponseEntity response = null;
        if (odontologoService.buscarOdontologo(odontologo.getId())== null ){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            odontologoService.actualizarOdontologo(odontologo);
            response = new ResponseEntity(HttpStatus.OK);
        }
        return response;

    }

}
