package Services;

import Entity.Well;
import org.sqlite.JDBC;

import java.sql.*;
import java.util.*;

public class WellService {

    // Используем шаблон одиночка, чтобы не плодить множество
    // экземпляров класса DbHandler
    private static WellService instance = null;

    public static synchronized WellService getInstance(String pathSQLDatabase)  {
        if (instance == null)
            instance = new WellService(pathSQLDatabase);
        return instance;
    }

    // Объект, в котором будет храниться соединение с БД
    private Connection connection;

    private WellService(String pathSQLDatabase)  {

        try {
            DriverManager.registerDriver(new JDBC());
            this.connection = DriverManager.getConnection(pathSQLDatabase);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void addEquipmentForWell(String wellName) {}



    public void createWell(String wellName) {
        String sql = "INSERT INTO 'well'  VALUES (null, (?))";
        try (final PreparedStatement statement =
                     //сюда передаете sql запрос:
                     connection.prepareStatement(sql)) {

            //Тут у вас задаются значения для (?).
            //Вместо (?) подставляете значение ("test"). Индексация параметров(wildcard) - (?) начинается от 1.
            statement.setString(1, wellName);

            //Выполняете сам запрос в базу.
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public void soutWell() throws SQLException, SQLException {
        System.out.println(connection.createStatement().executeQuery("SELECT * FROM well").getStatement());


        ResultSet resSet = connection.createStatement().executeQuery("SELECT * FROM well");

        while(resSet.next())
        {
            int id = resSet.getInt("id");
            String  name = resSet.getString("name");
            System.out.println(id+" | "+name);
        }

    }
}