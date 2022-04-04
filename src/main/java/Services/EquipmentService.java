package Services;

import Entity.Equipment;
import Entity.Well;
import org.sqlite.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class EquipmentService {

    private static final String CON_STR = "jdbc:sqlite:C:/Users/VacuouS/IdeaProjects/SQLiteJava/src/main/resources/well.db";

    // Используем шаблон одиночка, чтобы не плодить множество
    // экземпляров класса DbHandler
    private static EquipmentService instance = null;

    public static synchronized EquipmentService getInstance() throws SQLException {
        if (instance == null)
            instance = new EquipmentService();
        return instance;
    }

    // Объект, в котором будет храниться соединение с БД
    private Connection connection;

    private EquipmentService() throws SQLException {
        // Регистрируем драйвер, с которым будем работать
        // в нашем случае Sqlite
        DriverManager.registerDriver(new JDBC());
        // Выполняем подключение к базе данных
        this.connection = DriverManager.getConnection(CON_STR);
    }
    public void addEquipment(Equipment equipment) {}
    public List<Equipment> getAllEquipment(){
        return null;
    }
    public void createEquipment(int countEquipment,String wellName){

        //Проверка на существование такой скважины


        for(int i=0;i<countEquipment;i++){


            //Генерируем имя.

            //Создаём оборудование.


        }

    }
}
