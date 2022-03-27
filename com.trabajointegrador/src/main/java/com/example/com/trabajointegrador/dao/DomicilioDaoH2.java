package com.example.com.trabajointegrador.dao;

import com.example.com.trabajointegrador.entidades.Domicilio;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DomicilioDaoH2 implements IDao<Domicilio> {

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/clinica_maven";
    private final static String DB_USER ="sa";
    private final static String DB_PASSWORD = "";
    private final static Logger logger = Logger.getLogger(DomicilioDaoH2.class);

    @Override
    public Domicilio registrar(Domicilio domicilio) {

        Connection connection= null;
        PreparedStatement preparedStatement=null;
        try {

            logger.info("Init connection to DB");

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            logger.info("Connection SUCCESS");

            preparedStatement= connection.prepareStatement("INSERT INTO domicilio(calle, numero, localidad, provincia) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,domicilio.getCalle());
            preparedStatement.setInt(2,domicilio.getNumero());
            preparedStatement.setString(3,domicilio.getLocalidad());
            preparedStatement.setString(4,domicilio.getProvincia());

            preparedStatement.executeUpdate();

            logger.info("Try execute Insert query");

            ResultSet keys =preparedStatement.getGeneratedKeys();
            if (keys.next())
                domicilio.setId(keys.getInt(1));

            logger.info("Execute Insert query SUCCESS " + domicilio);
            preparedStatement.close();


        }catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return domicilio;

    }

    @Override
    public Domicilio buscar(int id) {

        Connection connection= null;
        PreparedStatement preparedStatement=null;
        Domicilio domicilio= null;

        try {

            logger.info("Init connection to DB");

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            logger.info("Connection SUCCESS");

            preparedStatement = connection.prepareStatement("SELECT * FROM domicilio WHERE id=?");
            preparedStatement.setLong(1, id);

            ResultSet results = preparedStatement.executeQuery();

            logger.info("Try execute Searching query");

            while (results.next()) {
                int idDomicilio = results.getInt("id");
                String calleDomicilio = results.getString("calle");
                int numeroDomicilio = results.getInt("numero");
                String localidadDomicilio = results.getString("localidad");
                String provinciaDomicilio = results.getString("provincia");

                domicilio = new Domicilio();
                domicilio.setId(idDomicilio);
                domicilio.setCalle(calleDomicilio);
                domicilio.setNumero(numeroDomicilio);
                domicilio.setLocalidad(localidadDomicilio);
                domicilio.setProvincia(provinciaDomicilio);
                logger.info("Se realizó satisfactoriamente la búsqueda del domicilio " + domicilio);

            }

            preparedStatement.close();
        }catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }

            return domicilio;
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

            preparedStatement= connection.prepareStatement("DELETE FROM domicilio WHERE id=?");
            preparedStatement.setLong(1,id);

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
    public List<Domicilio> buscarTodos() {

        Connection connection= null;
        PreparedStatement preparedStatement=null;
        ArrayList<Domicilio> domicilios= new ArrayList<>();
        Domicilio domicilio = null;

        try {

            logger.info("Init connection to DB");

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            logger.info("Connection SUCCESS");

            preparedStatement = connection.prepareStatement("SELECT * FROM domicilio");

            logger.info("Try execute Delete query");

            ResultSet results = preparedStatement.executeQuery();

            while (results.next()){
                int idDomicilio = results.getInt("id");
                String calleDomicilio= results.getString("calle");
                int numeroDomicilio= results.getInt("numero");
                String localidadDomicilio= results.getString("localidad");
                String provinciaDomicilio= results.getString("provincia");

                domicilio = new Domicilio();
                domicilio.setId(idDomicilio);
                domicilio.setCalle(calleDomicilio);
                domicilio.setNumero(numeroDomicilio);
                domicilio.setLocalidad(localidadDomicilio);
                domicilio.setProvincia(provinciaDomicilio);

                domicilios.add(domicilio);
                logger.info("Se realizó satisfactoriamente la búsqueda del domicilio " + domicilio);
            }
            preparedStatement.close();


        }catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return domicilios;
    }
}
