package com.example.com.trabajointegrador.repository;

import com.example.com.trabajointegrador.model.Odontologo;
import com.example.com.trabajointegrador.model.Paciente;
import com.example.com.trabajointegrador.model.Turno;
import com.example.com.trabajointegrador.util.Util;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TurnosDaoH2 implements IDao<Turno>{

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/clinica_maven";
    private final static String DB_USER ="sa";
    private final static String DB_PASSWORD = "";
    private final static Logger logger = Logger.getLogger(TurnosDaoH2.class);
    private OdontologoDaoH2 odontologoDaoH2 = new OdontologoDaoH2();
    private PacienteDaoH2 pacienteDaoH2 = new PacienteDaoH2();

    @Override
    public Turno registrar(Turno turno) {

        Connection connection= null;
        PreparedStatement preparedStatement=null;

        try {
            logger.info("Init connection to DB");

            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            logger.info("Connection SUCCESS");
            logger.info("Preparing Query");

            preparedStatement = connection.prepareStatement("INSERT INTO turnos(paciente_id, odontologo_id, fecha_turno) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,turno.getPaciente().getId());
            preparedStatement.setInt(2,turno.getOdontologo().getId());
            preparedStatement.setDate(3, Util.utilDateToSqlDate(turno.getFechayHoraTurno()));

            logger.info("Try execute Insert query");

            preparedStatement.executeUpdate();

            ResultSet keys=preparedStatement.getGeneratedKeys();
            if (keys.next())
                turno.setId(keys.getInt(1));

            logger.info("Insert SUCCESS ");
            preparedStatement.close();


        }catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return turno;
    }

    @Override
    public Turno buscar(int id) {

        Connection connection= null;
        PreparedStatement preparedStatement=null;
        Turno turno = null;

        try {
            logger.info("Init connection to DB");

            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            logger.info("Connection SUCCESS");

            logger.info("Preparing Query");

            preparedStatement = connection.prepareStatement("SELECT * FROM turnos where id=?");
            preparedStatement.setInt(1,id);

            logger.info("Try execute query");
            ResultSet results = preparedStatement.executeQuery();
            logger.info("Execute query SUCCESS");

            while (results.next()){
                int idTurno = results.getInt("id");
                int idPaciente = results.getInt("paciente_id");
                int idOdontologo = results.getInt("odontologo_id");
                Date fechaTurno = results.getDate("fecha_turno");
                Paciente paciente = pacienteDaoH2.buscar(idPaciente);
                Odontologo odontologo = odontologoDaoH2.buscar(idOdontologo);

                turno = new Turno();
                turno.setId(idTurno);
                turno.setPaciente(paciente);
                turno.setOdontologo(odontologo);
                turno.setFechayHoraTurno(fechaTurno);

                logger.info("Se realizó satisfactoriamente la búsqueda del turno ");
            }

            preparedStatement.close();

        }catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return turno;
    }

    @Override
    public void eliminar(int id) {

        Connection connection= null;
        PreparedStatement preparedStatement=null;

        try {
            logger.info("Init connection to DB");

            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            logger.info("Connection SUCCESS");
            logger.info("Preparing Query");

            preparedStatement = connection.prepareStatement("DELETE FROM turnos WHERE id=?");
            preparedStatement.setInt(1,id);

            logger.info("Try execute Delete query");

            preparedStatement.executeUpdate();
            logger.info("DELETE SUCCESS");
            preparedStatement.close();


        }catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Turno> buscarTodos() {

        Connection connection= null;
        PreparedStatement preparedStatement=null;
        ArrayList<Turno> turnos= new ArrayList<>();
        Turno turno = null;

        try {
            logger.info("Init connection to DB");

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            logger.info("Connection SUCCESS");
            logger.info("Preparing Query");

            preparedStatement = connection.prepareStatement("SELECT * FROM turnos");

            logger.info("Try execute query");

            ResultSet results = preparedStatement.executeQuery();

            while (results.next()){

                int idTurno = results.getInt("id");
                int idPaciente = results.getInt("paciente_id");
                int idOdontologo = results.getInt("odontologo_id");
                Date fechaTurno = results.getDate("fecha_turno");
                Paciente paciente = pacienteDaoH2.buscar(idPaciente);
                Odontologo odontologo = odontologoDaoH2.buscar(idOdontologo);

                turno = new Turno();
                turno.setId(idTurno);
                turno.setPaciente(paciente);
                turno.setOdontologo(odontologo);
                turno.setFechayHoraTurno(fechaTurno);

                turnos.add(turno);
            }
            logger.info("Se realizó satisfactoriamente la búsqueda de todos los turnos   ");
            preparedStatement.close();

        }catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return turnos;
    }

    @Override
    public Turno modificar(Turno turno) {
        Connection connection= null;
        PreparedStatement preparedStatement=null;

        try {

            logger.info("Init connection to DB");

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            logger.info("Connection SUCCESS");

            preparedStatement = connection.prepareStatement("UPDATE turno SET paciente_id = ?, odontologo_id = ?, fecha_turno = ? WHERE ID = ?");
            preparedStatement.setInt(1,turno.getPaciente().getId());
            preparedStatement.setInt(2,turno.getOdontologo().getId());
            preparedStatement.setDate(3, Util.utilDateToSqlDate(turno.getFechayHoraTurno()));
            preparedStatement.setInt(4,turno.getId());

            preparedStatement.executeUpdate();

            logger.info("Try execute Update query");
            logger.info("Execute Update query SUCCESS " );
            preparedStatement.close();

        }catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return turno;
    }
}
