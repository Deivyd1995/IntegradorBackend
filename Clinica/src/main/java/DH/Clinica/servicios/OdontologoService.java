package DH.Clinica.servicios;


import DH.Clinica.entity.Odontologo;
import DH.Clinica.repository.IDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService {

    private IDao<Odontologo> odontologoIDao;

    public IDao<Odontologo> getOdontologoIDao() {
        return odontologoIDao;
    }

    public void setOdontologoIDao(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public Odontologo registrarOdontologo(Odontologo odontologo){
        return this.odontologoIDao.registrar(odontologo);
    }

    public Odontologo buscarOdontologo(int id){
        return this.odontologoIDao.buscar(id);
    }

    public void eliminarOdontolgo(int id){
        this.odontologoIDao.eliminar(id);
    }

    public List<Odontologo> buscarTodosOdontologos(){
        return this.odontologoIDao.buscarTodos();
    }

    public Odontologo actualizarOdontologo(Odontologo odontologo){
        return this.odontologoIDao.modificar(odontologo);
    }

}
