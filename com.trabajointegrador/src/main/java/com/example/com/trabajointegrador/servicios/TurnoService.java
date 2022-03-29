package com.example.com.trabajointegrador.servicios;

import com.example.com.trabajointegrador.dao.IDao;
import com.example.com.trabajointegrador.entidades.Odontologo;
import com.example.com.trabajointegrador.entidades.Turno;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {

    private IDao<Turno> turnoIDao;

    public IDao<Turno> getTurnoIDao() {
        return turnoIDao;
    }

    public void setTurnoIDao(IDao<Turno> turnoIDao) {
        this.turnoIDao = turnoIDao;
    }

    public Turno registrarTurno(Turno turno){
        return this.turnoIDao.registrar(turno);
    }

    public Turno buscarTurno(int id){
        return this.turnoIDao.buscar(id);
    }

    public void eliminarTurno(int id){
       this.turnoIDao.eliminar(id);
    }

    public List<Turno> buscarTodosTurnos(){
        return this.turnoIDao.buscarTodos();
    }

    public Turno actualizarTurno(Turno turno) {
        return this.turnoIDao.modificar(turno);
    }
}
