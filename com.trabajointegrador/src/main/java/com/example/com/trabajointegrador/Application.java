package com.example.com.trabajointegrador;

import com.example.com.trabajointegrador.repository.OdontologoDaoH2;
import com.example.com.trabajointegrador.repository.PacienteDaoH2;
import com.example.com.trabajointegrador.repository.TurnosDaoH2;
import com.example.com.trabajointegrador.entidades.Paciente;
import com.example.com.trabajointegrador.servicios.OdontologoService;
import com.example.com.trabajointegrador.servicios.PacienteService;
import com.example.com.trabajointegrador.servicios.TurnoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		PacienteService pacienteService = new PacienteService();
		OdontologoService odontologoService =new OdontologoService();
		pacienteService.setPacienteIDao(new PacienteDaoH2());
		odontologoService.setOdontologoIDao( new OdontologoDaoH2());
		TurnoService turnoService = new TurnoService();
	    turnoService.setTurnoIDao(new TurnosDaoH2());


		Paciente paciente = new Paciente();
		paciente.setId(1);
		paciente.setNombre("Enrique");
		paciente.setApellido("Maynard");
		paciente.setDNI(3893946);
		paciente.setFechaIngreso(new Date());



		//pacienteService.actualizarPaciente(paciente);
		pacienteService.buscarTodosPacientes();





	}
}
