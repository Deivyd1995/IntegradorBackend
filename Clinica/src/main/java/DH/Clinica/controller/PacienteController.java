package DH.Clinica.controller;



import DH.Clinica.entity.Paciente;
import DH.Clinica.repository.PacienteDaoH2;
import DH.Clinica.servicios.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {


    @Autowired
    PacienteService pacienteService;

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
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity eliminar(@PathVariable int id){
        pacienteService.setPacienteIDao(new PacienteDaoH2());
        ResponseEntity response = null;
        if (pacienteService.buscarPaciente(id) == null){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            pacienteService.eliminarPaciente(id);
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return response;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Paciente> actualizar(@RequestBody Paciente paciente){
            pacienteService.setPacienteIDao(new PacienteDaoH2());
            ResponseEntity response = null;
            if (pacienteService.buscarPaciente(paciente.getId())== null ){
                response = new ResponseEntity(HttpStatus.NOT_FOUND);
            }else{
                pacienteService.actualizarPaciente(paciente);
                response = new ResponseEntity(HttpStatus.OK);
            }
            return response;

    }

}


