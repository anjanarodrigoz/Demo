package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Database instance;
    private final Connection connection;

    private Database(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/pos";
            String username = "root";
            String password = "12345678";
            connection = DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static Database getInstance(){
        if(instance==null){
            instance = new Database();
        }

        return instance;
    }

    public Connection getConnection(){
        return  connection;
    }
}
