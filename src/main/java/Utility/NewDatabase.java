package Utility;
import org.sqlite.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class NewDatabase {
    private static Connection connection;
    public static void createDatabase(String pass){

        try {

            DriverManager.registerDriver(new JDBC());
            // Выполняем подключение к базе данных
            connection = DriverManager.getConnection(pass);
            Statement statmt = connection.createStatement();
            statmt.execute(
                    "PRAGMA foreign_keys=on;\n" +
                            "CREATE TABLE well( \n" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                            "name VARCHAR(32) NOT NULL\n" +
                            ");+"+
                            "CREATE TABLE equipment(\n" +
                            "  id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, \n" +
                            "name VARCHAR(32) NOT NULL,\n" +
                            "  well_id INTEGER NOT NULL,\n" +
                            "  FOREIGN KEY(well_id) REFERENCES well(id)\n" +
                            ");\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Таблица создана или уже существует.");
    }

    /*
    public static void CreateDB() throws ClassNotFoundException, SQLException
	   {
		statmt = conn.createStatement();
		statmt.execute("CREATE TABLE if not exists 'users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'phone' INT);");

		System.out.println("Таблица создана или уже существует.");
	   }*/
}
