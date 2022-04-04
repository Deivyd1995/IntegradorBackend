package DH.Clinica.controller;



import DH.Clinica.entity.Odontologo;
import DH.Clinica.repository.OdontologoDaoH2;
import DH.Clinica.servicios.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    OdontologoService odontologoService;

    @GetMapping("/id/{odontologoId}")
    public Odontologo getTurno(@PathVariable int odontologoId){
        odontologoService.setOdontologoIDao(new OdontologoDaoH2());
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
