import Services.EquipmentService;
import Services.WellService;
import Utility.NewDatabase;
import Utility.NewFileBD;

import java.nio.ByteBuffer;
import java.sql.*;
import java.sql.SQLException;
import java.util.Locale;
import java.util.UUID;

public class StartProgramm {
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;


    private static final String CON_STR = "jdbc:sqlite:well.db";
    private static WellService wellService=WellService.getInstance(CON_STR);
    private static EquipmentService equipmentService=EquipmentService.getInstance(CON_STR);


    public static String shortUUID() {
        UUID uuid = UUID.randomUUID();
        long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
        return Long.toString(l, Character.MAX_RADIX).toUpperCase(Locale.ROOT);
    }

    public static void main(String[] args) {

        System.out.println("Выберите функцию:");
        System.out.println("1-Создание N кол-ва оборудования на скважине.");
        System.out.println("2-Вывод общей информации об оборудовании на скважинах.");
        System.out.println("3-Экспорт всех данных в xml файл.");

        int choose=2;

        switch (choose) {
            //В процессе
            case 1 :
                createNEquipment();
                break;
                //Сделано
            case 2:
                outputAllInformationAboutWell();
                break;
                //TODO Треш
            case 3:
                exportXMLFile();
                break;
        }
      /*  NewFileBD.createFile();

        NewDatabase.createDatabase(CON_STR);*/


    }
    private static void createNEquipment(){
        System.out.println("Введите имя скважины");
        String wellName="aa";
        System.out.println("Введите количество оборудования");
        int countEquipment=3;

        equipmentService.createEquipment(wellName,countEquipment);
    }
    private static void outputAllInformationAboutWell(){
        System.out.println("Введите имена скважин через ','");
        String strName="aa,bb,cc";
        String[] arrName=strName.split(",");
        for(String wellName:arrName){
            equipmentService.countEquipment(wellName);
        }

    }
    private static void exportXMLFile(){

    }
}
