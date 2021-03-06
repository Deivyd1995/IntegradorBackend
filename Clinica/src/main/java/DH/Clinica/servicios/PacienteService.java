package DH.Clinica.servicios;



import DH.Clinica.entity.Paciente;
import DH.Clinica.repository.IDao;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PacienteService {

    private IDao<Paciente> pacienteIDao;

    public IDao<Paciente> getPacienteIDao() {
        return pacienteIDao;
    }

    public void setPacienteIDao(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    public Paciente registrarPaciente(Paciente paciente){
        return this.pacienteIDao.registrar(paciente);
    }

    public Paciente buscarPaciente(int id){
        return this.pacienteIDao.buscar(id);
    }

    public void eliminarPaciente(int id){
        this.pacienteIDao.eliminar(id);
    }

    public List<Paciente> buscarTodosPacientes(){
        return this.pacienteIDao.buscarTodos();
    }

    public Paciente actualizarPaciente(Paciente paciente){
        return this.pacienteIDao.modificar(paciente);
    }

}
