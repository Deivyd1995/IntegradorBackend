package com.example.com.trabajointegrador.dao;


import com.example.com.trabajointegrador.entidades.Domicilio;
import com.example.com.trabajointegrador.entidades.Paciente;
import com.example.com.trabajointegrador.util.Util;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PacienteDaoH2 implements IDao<Paciente>{

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/clinica_maven";
    private final static String DB_USER ="sa";
    private final static String DB_PASSWORD = "";
    private final static Logger logger = Logger.getLogger(PacienteDaoH2.class);
    private DomicilioDaoH2 domicilioDaoH2 = new DomicilioDaoH2();


    @Override
    public Paciente registrar(Paciente paciente) {

        Connection connection= null;
        PreparedStatement preparedStatement=null;

        try {
            logger.info("Init connection to DB");

            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            logger.info("Connection SUCCESS");

            Domicilio domicilio = domicilioDaoH2.registrar(paciente.getDomicilio());
            paciente.getDomicilio().setId(domicilio.getId());

            preparedStatement = connection.prepareStatement("INSERT INTO paciente(nombre, apellido,dni,fecha_ingreso,domicilio_id) VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,paciente.getNombre());
            preparedStatement.setString(2, paciente.getApellido());
            preparedStatement.setInt(3,paciente.getDNI());
            preparedStatement.setDate(4, Util.utilDateToSqlDate(paciente.getFechaIngreso()));
            preparedStatement.setLong(5,paciente.getDomicilio().getId());

            logger.info("Try execute Insert query");

            preparedStatement.executeUpdate();

            ResultSet keys=preparedStatement.getGeneratedKeys();
            if (keys.next())
                paciente.setId(keys.getInt(1));

            logger.info("Insert SUCCESS " + paciente);
            preparedStatement.close();


        }catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paciente;

    }

    @Override
    public Paciente buscar(int id) {

        Connection connection= null;
        PreparedStatement preparedStatement=null;
        Paciente paciente=null;

        try {

            logger.info("Init connection to DB");

            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            logger.info("Connection SUCCESS");

            preparedStatement = connection.prepareStatement("SELECT * FROM paciente WHERE id=?");
            preparedStatement.setInt(1,id);

            logger.info("Try execute query");
            ResultSet results = preparedStatement.executeQuery();
            logger.info("Execute query SUCCESS");

            while (results.next()){

                int idPaciente = results.getInt("id");
                String nombrePaciente=results.getString("nombre");
                String apellidoPaciete =results.getString("apellido");
                int dniPaciente=results.getInt("dni");
                Date fecha_ingresoPaciente=results.getDate("fecha_ingreso");
                int idDomicilio=results.getInt("domicilio_id");
                Domicilio domicilio = domicilioDaoH2.buscar(idDomicilio);

                paciente = new Paciente();
                paciente.setId(idPaciente);
                paciente.setNombre(nombrePaciente);
                paciente.setApellido(apellidoPaciete);
                paciente.setDNI(dniPaciente);
                paciente.setFechaIngreso(fecha_ingresoPaciente);
                paciente.setDomicilio(domicilio);
                logger.info("Se realizó satisfactoriamente la búsqueda del paciente " + paciente);
            }

            preparedStatement.close();

        }catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paciente;
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

            preparedStatement = connection.prepareStatement("DELETE FROM paciente WHERE id=?");
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
    public List<Paciente> buscarTodos() {

        Connection connection= null;
        PreparedStatement preparedStatement=null;
        ArrayList<Paciente> pacientes= new ArrayList<>();
        Paciente paciente = null;

        try {

            logger.info("Init connection to DB");

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            logger.info("Connection SUCCESS");

            preparedStatement = connection.prepareStatement("SELECT * FROM paciente");

            logger.info("Try execute query");

            ResultSet results = preparedStatement.executeQuery();

            while (results.next()){

                int idPaciente = results.getInt("id");
                String nombrePaciente=results.getString("nombre");
                String apellidoPaciete =results.getString("apellido");
                int dniPaciente=results.getInt("dni");
                Date fecha_ingresoPaciente=results.getDate("fecha_ingreso");
                int idDomicilio=results.getInt("domicilio_id");
                Domicilio domicilio = domicilioDaoH2.buscar(idDomicilio);

                paciente = new Paciente();
                paciente.setId(idPaciente);
                paciente.setNombre(nombrePaciente);
                paciente.setApellido(apellidoPaciete);
                paciente.setDNI(dniPaciente);
                paciente.setFechaIngreso(fecha_ingresoPaciente);
                paciente.setDomicilio(domicilio);


                pacientes.add(paciente);

            }
            logger.info("Se realizó satisfactoriamente la búsqueda del paciente " + pacientes);
            preparedStatement.close();


        }catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pacientes;
    }
}
