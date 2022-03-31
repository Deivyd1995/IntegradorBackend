package DH.Clinica.model;

import javax.persistence.Entity;
import java.util.Date;


public class Paciente {

        private int id;
        private String nombre;
        private String apellido;
        private int DNI;
        private Date fechaIngreso;
        private Domicilio domicilio;

        public Paciente() {
            this.id = id;
            this.nombre = nombre;
            this.apellido = apellido;
            this.DNI = DNI;
            this.fechaIngreso = fechaIngreso;
            this.domicilio = domicilio;
        }

        public Paciente(String nombre, String apellido, int DNI, Date fechaIngreso, Domicilio domicilio) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.DNI = DNI;
            this.fechaIngreso = fechaIngreso;
            this.domicilio = domicilio;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }

        public int getDNI() {
            return DNI;
        }

        public void setDNI(int DNI) {
            this.DNI = DNI;
        }

        public Date getFechaIngreso() {
            return fechaIngreso;
        }

        public void setFechaIngreso(Date fechaIngreso) {
            this.fechaIngreso = fechaIngreso;
        }

        public Domicilio getDomicilio() {
            return domicilio;
        }

        public void setDomicilio(Domicilio domicilio) {
            this.domicilio = domicilio;
        }

        @Override
        public String toString() {
            return "Paciente{" +
                    "id=" + id +
                    ", nombre='" + nombre + '\'' +
                    ", apellido='" + apellido + '\'' +
                    ", DNI=" + DNI +
                    ", fechaIngreso=" + fechaIngreso +
                    ", domicilio=" + domicilio +
                    '}';
        }

}
