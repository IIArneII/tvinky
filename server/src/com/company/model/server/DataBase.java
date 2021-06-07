package com.company.model.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    Connection connection;
    public  Connection ConnectionWithDataBase() throws Exception{
        Class.forName("org.postgresql.Driver");
        String UserName = "postgres";
        String Password = "novi48001";
        System.out.println("Getting connection...");
        String URL = "jdbc:postgresql://localhost:5432/Tvinky";
        System.out.println("OK");
        try {

            connection = DriverManager.getConnection(URL, UserName, Password);
            Server.ConnectDataBase = true;
            return connection;
        }
        catch (Exception e) {
            Server.ConnectDataBase = false;
        }
    }

    public static void DataInput(String UserLogin) throws SQLException, ClassNotFoundException {
        //мы проверяем, если ли уже такой пользователь и после добавляем в базу данных
        try {
            Statement create = connection.createStatement();
            //ResultSet result = create.executeQuery("select * from \"players\" where login = 'UserLogin' ");
            ResultSet count = create.executeQuery("select count(*) from \"players\" where login = '" + UserLogin + "' ");
            if(count.getInt(1) != 0)
            {
                System.out.println("Такой пользователь уже существует!");
            }
            else {
                create.executeUpdate("INSERT INTO \"players\" (login) VALUES ('" + UserLogin + "')");
            }

        } catch (Exception e) {
            Server.ConnectDataBase = false;
        }
    }

}
