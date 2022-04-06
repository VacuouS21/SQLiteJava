package Services;

import Utility.ForCheckWellWithName;
import Utility.NameForEquipment;
import org.sqlite.JDBC;

import java.sql.*;

public class EquipmentService {

    //Количество оборудования для всех скважин, для уникальных имён.
    private static int countAllEquipment = 0;

    //Singletone
    private static EquipmentService instance = null;
    public static synchronized EquipmentService getInstance(String pathSQLDatabase) {
        if (instance == null)
            instance = new EquipmentService(pathSQLDatabase);
        return instance;
    }



    private Connection connection;

    //Работа с бд
    private EquipmentService(String pathSQLDatabase) {
        try {
            DriverManager.registerDriver(new JDBC());
            this.connection = DriverManager.getConnection(pathSQLDatabase);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Создание n оборудования для скважины wellName
    public void createEquipment(String wellName, int countClientEquipment) {

        try {
            //Создаём well с заданным wellName, если такой ещё нет
            if(!ForCheckWellWithName.isForCheckWell(wellName,connection).getBoolean(1)) {
                ForCheckWellWithName.createWell(wellName,connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //

        //Получение общего количества оборудования, для уникального имени.
        numCountAllEquipment();

        //Нахождение id скважины по её имени, для дальнейшей записи в well_id у equipment
        int idWellWithClientName=findIdWithName(wellName);

        if(idWellWithClientName==-1){
            System.out.println("Такая скважина отсутсвует");
        }
       else {
            //Создание ун. имени и запись в в базу
            for (int i = 0; i < countClientEquipment; i++) {

                //Генерируем имя.
                String nameUnique = NameForEquipment.createName(countAllEquipment);
                //Для уникального имени.
                countAllEquipment++;
                //Создаём оборудование.
                doNewEquipment(idWellWithClientName, nameUnique);
            }
        }
    }

    //ВТорой выбор, для подсчёта количества оборудования на скважине wellName
    public void countEquipment(String wellName) {
        String sql = "SELECT count() FROM equipment JOIN well w on w.id = equipment.well_id WHERE w.name=(?);";
        //Работа с бд
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, wellName);
            System.out.println("Кол-во оборудования на скважине " + wellName + ": " + statement.executeQuery().getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //подсчёт всего оборудования, для уникального имени
    private void numCountAllEquipment() {
        try {
            this.countAllEquipment = this.connection.createStatement().executeQuery("SELECT count() FROM equipment").getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Добавление новых оборудованием.
    private void doNewEquipment(int wellId,String equipmentName){
        String sqlForAddEquipment = "INSERT INTO equipment  VALUES (null,(?),(?))";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlForAddEquipment);
            statement.setString(1, equipmentName);
            statement.setInt(2, wellId);
            statement.executeUpdate();
            //Выполняете сам запрос в базу.
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Поиск Id скважины по её имени, для добавления новых оборудований к этой скважине.
    private int findIdWithName(String wellName){

        String sqlForNameWell = "SELECT id FROM well Where name =(?);";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlForNameWell);
            statement.setString(1, wellName);
            return statement.executeQuery().getInt(1);
            //Выполняете сам запрос в базу.
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
