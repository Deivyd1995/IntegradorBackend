package com.example.com.trabajointegrador.repository;


import com.example.com.trabajointegrador.entidades.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/clinica_maven";
    private final static String DB_USER ="sa";
    private final static String DB_PASSWORD = "";
    private final static Logger logger = Logger.getLogger(OdontologoDaoH2.class);


    @Override
    public Odontologo registrar(Odontologo odontologo) {

        Connection connection = null;
        PreparedStatement preparedStatement=null;

        try {

            logger.info("Init connection to DB");

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            logger.info("Connection SUCCESS");

            preparedStatement = connection.prepareStatement("INSERT INTO odontologo(numero_matricula, nombre, apellido) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1,odontologo.getNumeroMatricula());
            preparedStatement.setString(2,odontologo.getNombre());
            preparedStatement.setString(3,odontologo.getApellido());

            preparedStatement.executeUpdate();

            logger.info("Try execute Insert query");

            ResultSet keys=preparedStatement.getGeneratedKeys();
            if (keys.next())
                odontologo.setId(keys.getInt(1));

            logger.info("Execute Insert query SUCCESS " + odontologo);
            preparedStatement.close();


        }catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return odontologo;
    }

    @Override
    public Odontologo buscar(int id) {

        Connection connection= null;
        PreparedStatement preparedStatement=null;
        Odontologo odontologo= null;

        try {

            logger.info("Init connection to DB");

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            logger.info("Connection SUCCESS");

            preparedStatement = connection.prepareStatement("SELECT * FROM odontologo WHERE id=?");
            preparedStatement.setInt(1,id);

            ResultSet results = preparedStatement.executeQuery();
            logger.info("Try execute Searching query");

            while (results.next()){
                int idOdontologo = results.getInt("id");
                int numeroMatriculaOdontologo= results.getInt("numero_matricula");
                String nombreOdontologo= results.getString("nombre");
                String apellidoOdontologo= results.getString("apellido");

                odontologo= new Odontologo();
                odontologo.setId(idOdontologo);
                odontologo.setNumeroMatricula(numeroMatriculaOdontologo);
                odontologo.setNombre(nombreOdontologo);
                odontologo.setApellido(apellidoOdontologo);
                logger.info("Search SUCCESS " + odontologo);
            }

            preparedStatement.close();

        }catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return odontologo;
    }

    @Override
    public void eliminar(int id) {

        Connection connection= null;
        PreparedStatement preparedStatement=null;

        try {
            logger.info("Init connection to DB");

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            logger.info("Connection SUCCESS");

            preparedStatement = connection.prepareStatement("DELETE FROM odontologo WHERE id=?");
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
    public List<Odontologo> buscarTodos() {

        Connection connection= null;
        PreparedStatement preparedStatement=null;
        ArrayList<Odontologo> odontologos = new ArrayList<>();
        Odontologo odontologo= null;

        try {

            logger.info("Init connection to DB");

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            logger.info("Connection SUCCESS");

            preparedStatement = connection.prepareStatement("SELECT * FROM odontologo");

            ResultSet results = preparedStatement.executeQuery();

            logger.info("Try execute Searching query");

            while (results.next()){
                int idOdontologo = results.getInt("id");
                int numeroMatriculaOdontologo= results.getInt("numero_matricula");
                String nombreOdontologo= results.getString("nombre");
                String apellidoOdontologo= results.getString("apellido");

                odontologo= new Odontologo();
                odontologo.setId(idOdontologo);
                odontologo.setNumeroMatricula(numeroMatriculaOdontologo);
                odontologo.setNombre(nombreOdontologo);
                odontologo.setApellido(apellidoOdontologo);

                odontologos.add(odontologo);
                logger.info("Se realizó satisfactoriamente la búsqueda del odontologo " + odontologo);
            }
            preparedStatement.close();

        }catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return odontologos;
    }

    @Override
    public Odontologo modificar(Odontologo odontologo) {
        Connection connection= null;
        PreparedStatement preparedStatement=null;

        try {

            logger.info("Init connection to DB");

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            logger.info("Connection SUCCESS");

            preparedStatement = connection.prepareStatement("UPDATE odontologo SET numero_matricula = ?, nombre = ?, apellido = ? WHERE ID = ?");
            preparedStatement.setInt(1,odontologo.getNumeroMatricula());
            preparedStatement.setString(2,odontologo.getNombre());
            preparedStatement.setString(3,odontologo.getApellido());
            preparedStatement.setInt(4,odontologo.getId());

            preparedStatement.executeUpdate();

            logger.info("Try execute Update query");
            logger.info("Execute Update query SUCCESS ");
            preparedStatement.close();

        }catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return odontologo;

    }
}

