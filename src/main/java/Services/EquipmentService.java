package Services;

import Entity.Equipment;
import Entity.Well;
import org.sqlite.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class EquipmentService {



    // Используем шаблон одиночка, чтобы не плодить множество
    // экземпляров класса DbHandler
    private static EquipmentService instance = null;

    public static synchronized EquipmentService getInstance(String pathSQLDatabase)  {
        if (instance == null)
            instance = new EquipmentService(pathSQLDatabase);
        return instance;
    }

    // Объект, в котором будет храниться соединение с БД
    private Connection connection;

    private EquipmentService(String pathSQLDatabase) {
        // Регистрируем драйвер, с которым будем работать
        // в нашем случае Sqlite
        try {
            DriverManager.registerDriver(new JDBC());
            this.connection = DriverManager.getConnection(pathSQLDatabase);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Выполняем подключение к базе данных

    }
    public void createEquipment(String wellName,int countEquipment){

        //Проверка на существование такой скважины


        for(int i=0;i<countEquipment;i++){

            //Генерируем имя.

            //Создаём оборудование.


        }

    }
    public void countEquipment(String wellName){
        String sql ="SELECT count() FROM equipment JOIN well w on w.id = equipment.well_id WHERE w.name=(?);";
        try {
            PreparedStatement statement =connection.prepareStatement(sql);
            statement.setString(1, wellName);

            //Выполняете сам запрос в базу.
            System.out.println("Кол-во оборудования на скважине "+wellName+": "+statement.executeQuery().getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //сюда передаете sql запрос:

    }
}
