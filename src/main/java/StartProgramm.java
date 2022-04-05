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

        int choose=1;

        switch (choose) {
            case 1 :
                break;
            case 2:
                break;
            case 3:
                break;
        }
      /*  NewFileBD.createFile();

        NewDatabase.createDatabase(CON_STR);*/


    }
    private static void createNEquipment(){

    }
    private static void outputAllInformationAboutWell(){

    }
    private static void exportXMLFile(){

    }
}
