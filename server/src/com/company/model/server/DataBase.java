package com.company.model.server;

import java.sql.*;

public class DataBase {
    java.sql.Connection connection;

    public DataBase(String url, String user, String pass) throws Exception{
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(url, user, pass);
    }

    public void DataInput(String UserLogin) throws SQLException, ClassNotFoundException {
        //мы проверяем, если ли уже такой пользователь и после добавляем в базу данных
        Statement create = connection.createStatement();
        ResultSet count = create.executeQuery("select count(*) from \"players\" where login = '" + UserLogin + "' ");
        count.next();
        if(count.getInt(1) != 0)
        {
            System.out.println("Такой пользователь уже существует!");
        }
        else {
            create.executeUpdate("INSERT INTO \"players\" (login) VALUES ('" + UserLogin + "')");
        }
    }

}
