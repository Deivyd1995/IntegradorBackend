package DH.Clinica.controller;



import DH.Clinica.model.Paciente;
import DH.Clinica.model.Turno;
import DH.Clinica.repository.TurnosDaoH2;
import DH.Clinica.servicios.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private TurnoService turnoService = new TurnoService();

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
    public Turno guardar(@RequestBody Turno turno){
        turnoService.setTurnoIDao(new TurnosDaoH2());
        return turnoService.registrarTurno(turno);
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
