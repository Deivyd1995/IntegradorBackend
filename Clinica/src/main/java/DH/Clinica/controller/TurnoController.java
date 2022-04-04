package DH.Clinica.controller;



import DH.Clinica.entity.Odontologo;
import DH.Clinica.entity.Paciente;
import DH.Clinica.entity.Turno;
import DH.Clinica.repository.OdontologoDaoH2;
import DH.Clinica.repository.PacienteDaoH2;
import DH.Clinica.repository.TurnosDaoH2;
import DH.Clinica.servicios.OdontologoService;
import DH.Clinica.servicios.PacienteService;
import DH.Clinica.servicios.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
   TurnoService turnoService;
    OdontologoService odontologoService;
    PacienteService pacienteService;

    @GetMapping("/id/{turnoId}")
    public Turno getTurno(@PathVariable int turnoId){
        turnoService.setTurnoIDao(new TurnosDaoH2());
        Turno turno = turnoService.buscarTurno(turnoId);
        return turno;
    }

    @GetMapping("/verTodos")
    public List<Turno> buscarTodos(){
        turnoService.setTurnoIDao(new TurnosDaoH2());
        return turnoService.buscarTodosTurnos();
    }

    @PostMapping("/registrar")
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno){
        turnoService.setTurnoIDao(new TurnosDaoH2());
        pacienteService.setPacienteIDao(new PacienteDaoH2());
        odontologoService.setOdontologoIDao(new OdontologoDaoH2());
        ResponseEntity<Turno> respuesta;
        //preguntar si es un paciente correcto y un odontologo correcto
        Paciente pacienteBus=pacienteService.buscarPaciente(turno.getPaciente().getId());
        Odontologo odontologoBus=odontologoService.buscarOdontologo(turno.getOdontologo().getId());

        if (pacienteBus!=null && odontologoBus!=null){
            respuesta= ResponseEntity.ok(turnoService.registrarTurno(turno));
        }
        else{
            respuesta=ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return respuesta;
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity eliminar(@PathVariable int id){
        turnoService.setTurnoIDao(new TurnosDaoH2());
        ResponseEntity response = null;
        if (turnoService.buscarTurno(id) == null){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            turnoService.eliminarTurno(id);
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return response;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Paciente> actualizar(@RequestBody Turno turno){
        turnoService.setTurnoIDao(new TurnosDaoH2());
        ResponseEntity response = null;
        if (turnoService.buscarTurno(turno.getId())== null ){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            turnoService.actualizarTurno(turno);
            response = new ResponseEntity(HttpStatus.OK);
        }
        return response;

    }


}
