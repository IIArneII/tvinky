package com.company.model.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    public static Connection ConnectionWithDataBase() throws Exception{
        Class.forName("org.postgresql.Driver");
        String UserName = "123456";
        String Password = "123456";
        String URL = "jdbc:postgresql://localhost:5432/Tvinky";
        try {

            Connection connection = DriverManager.getConnection(URL, UserName, Password);
            Server.ConnectDataBase = true;
            return connection;
        }
        catch (Exception e) {
            Server.ConnectDataBase = false;
        }
        return null;
    }

    public static void CheckRegistration() {
        // проверяет данные для регистрации аккаунта и если пользователь ввел все верно
        // то вызывает функцию DataInput() регистрацию в базе данных.

        // добавить проверку на корректность введённых данных
        if ((Controller.userNameFieldText.trim().length() <= 0) || (Controller.NameServerFieldText.trim().length() <= 0)){
            // сделать окошко для ошибки "Вы заполнили не все поля"
        }
        else {
            try {
                DataInput();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /*public static void Registration() {
        // проверяет данные введённые для входа в свой аккаунт и после вызывает LoginToAccount()
        // в котором проверяется есть ли такой пользователь в базе данных и совпадает ли пароль

        // Добавить проверку, все ли поля заполнены
        try {
            LoginToAccount();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/
    public static void DataInput() throws SQLException, ClassNotFoundException {
        //мы сверяем данные из базы данных с введёнными и после добавляем в базу данных
        try {
            Connection con = ConnectionToDateBase();
            Statement create = con.createStatement();
            create.executeUpdate("CREATE TABLE IF NOT EXISTS players (login CHAR(30) NOT NULL, server CHAR(30) NOT NULL, PRIMARY KEY (login))");
            ResultSet result = create.executeQuery("select * from players where login = '" + Controller.userNameFieldText + "'");
            if (result.next()){
                // окно: такой логин уже используется
            }else {
                create.executeUpdate("INSERT INTO players (login,server) VALUES ('" + Controller.userNameFieldText + "','" + Controller.NameServerFieldText + "')");
                Server.Answer();
            }
        } catch (Exception e) {
            Server.ConnectDataBase = false;
            Server.Answer();
        }
    }

    public static void LoginToAccount() throws SQLException, ClassNotFoundException {
        //мы сверяем данные с данными пользователя
        try {
            Connection connect = ConnectionWithDataBase();
            Statement create = connect.createStatement();
            //проверка на правильность
        } catch (Exception e) {
            Server.ConnectDataBase = false;
            Server.Answer();
        }
    }
}
