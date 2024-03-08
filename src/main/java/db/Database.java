package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Database instance;

    private final String url = "jdbc:mysql://localhost:3306/shop";
    private final String userName = "root";
    private final String password = "12345678";


    private Connection connection;

    private Database()  {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public static Database getInstance() {

        if(instance == null){
            instance = new Database();
        }

        return instance;
    }


    public Connection  getConnection(){

        return connection;
    }





}
