package com.example.com.trabajointegrador.controller;


import com.example.com.trabajointegrador.dao.PacienteDaoH2;
import com.example.com.trabajointegrador.entidades.Paciente;
import com.example.com.trabajointegrador.servicios.PacienteService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {



   private PacienteService pacienteService = new PacienteService();

    @GetMapping("/id/{userId}")
    public Paciente getPaciente(@PathVariable int userId){
        pacienteService.setPacienteIDao(new PacienteDaoH2());
        Paciente paciente = pacienteService.buscarPaciente(userId);
        return paciente;
    }

    @PostMapping("/registrar")
    public Paciente guardar(@RequestBody Paciente paciente){
        pacienteService.setPacienteIDao(new PacienteDaoH2());
        return pacienteService.registrarPaciente(paciente);
    }

    @GetMapping("/verTodos")
    public List<Paciente> buscarTodos(){
        pacienteService.setPacienteIDao(new PacienteDaoH2());
        return pacienteService.buscarTodosPacientes();
    }


}
