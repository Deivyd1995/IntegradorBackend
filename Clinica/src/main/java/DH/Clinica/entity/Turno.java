package DH.Clinica.entity;

import java.util.Date;


public class Turno {

    private int id;
    private Paciente paciente;
    private Odontologo odontologo;
    private Date fechayHoraTurno;

    public Turno(Paciente paciente, Odontologo odontologo, Date fechayHoraTurno) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fechayHoraTurno = fechayHoraTurno;
    }

    public Turno() {
        this.id = id;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fechayHoraTurno = fechayHoraTurno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public Date getFechayHoraTurno() {
        return fechayHoraTurno;
    }

    public void setFechayHoraTurno(Date fechayHoraTurno) {
        this.fechayHoraTurno = fechayHoraTurno;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", paciente=" + paciente +
                ", odontologo=" + odontologo +
                ", fechayHoraTurno=" + fechayHoraTurno +
                '}';
    }
}
